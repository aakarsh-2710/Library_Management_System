package com.tata.UserService.controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tata.UserService.constant.MessageConstant;
import com.tata.UserService.service.UserService;
import com.tata.UserService.util.CommonMethods;
import com.tata.UserService.vo.BookTransactionVO;
import com.tata.UserService.vo.BookVO;
import com.tata.UserService.vo.UserVo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	// we can ceate immutable objectd with constructor dependencies via using final
	// keyword
	// i.e private final UserService userService

	private UserService userService;

	private RestTemplate restTemplate;

	private CommonMethods commonMethods;

	@Autowired
	public UserController(UserService userService, RestTemplate restTemplate, CommonMethods commonMethods) {
		super();
		this.userService = userService;
		this.restTemplate = restTemplate;
		this.commonMethods = commonMethods;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers() throws Exception {
		List<UserVo> users = null;
		try {
			users = userService.findAllUsers();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return new ResponseEntity<>(users, HttpStatus.FOUND);
	}

	@GetMapping("/getById")
	public ResponseEntity<?> getUser(@RequestParam Integer userId) throws Exception {
		UserVo user = null;
		try {
			user = userService.findById(userId);
			if (user == null) {
				return commonMethods.errorMsg(MessageConstant.userNotFound);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return new ResponseEntity<>(user, HttpStatus.FOUND);

	}

	@PostMapping("/add")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserVo userVo) throws Exception {
		try {

			Boolean isUserPresent = userService.checkIfEmailExists(userVo.getEmailId());
			if (Boolean.TRUE.equals(isUserPresent)) {
				return commonMethods.errorMsg(MessageConstant.userAlreadyPtesent);
			}
			userService.saveUser(userVo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.userAddedSucceddfully);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestParam Integer userId) throws Exception {
		try {
			UserVo user = userService.findById(userId);
			if (user == null) {
				return commonMethods.errorMsg(MessageConstant.userNotFound);
			}

			Set<BookVO> books = user.getBooks();

			if (books != null && !books.isEmpty()) {
				// All books taken by that user should be returned
				for (BookVO bookVO : books) {
					bookVO.setAvailableCopies(bookVO.getAvailableCopies() + 1);
					restTemplate.put("http://BOOK-SERVICE/book/update", bookVO);
				}

				// when user is deleted its entry should be deleted from user_book table
				books.clear();
				userService.saveUser(user);
			}
			userService.deleteById(userId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.userDeletedSucceddfully);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserVo userVo) throws Exception {

		try {
			if (userVo.getUserId() == null) {
				return commonMethods.errorMsg(MessageConstant.userIdNotFound);
			}
			userService.saveUser(userVo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.userUpdatedSucceddfully);
	}

	@PostMapping("/getBookFromLibrary")
	public ResponseEntity<?> getBookFromLibrary(@Valid @RequestBody BookTransactionVO bookVO) throws Exception {
		try {
			UserVo userVo = userService.findById(bookVO.getUserId());
			if (userVo == null) {
				return commonMethods.errorMsg(MessageConstant.userNotFound);
			}

//			// check if this book is already assigned to the use
//			if (Boolean.TRUE.equals(checkIfBookIsAlreadyAssigned(bookVO, userVo))) {
//				return commonMethods.errorMsg(MessageConstant.bookAlreadyAssigned);
//			}
			ResponseEntity<BookVO> res = restTemplate
					.getForEntity("http://BOOK-SERVICE/book/getById?isdnNo=" + bookVO.getIsbn(), BookVO.class);
			BookVO bookVo = res.getBody();

			if (bookVo == null || bookVo.getBookId() == null || bookVo.getAvailableCopies() == 0) {
				return commonMethods.errorMsg(MessageConstant.bookNotFound);
			}

			bookVo.setAvailableCopies(bookVo.getAvailableCopies() - 1);

			userService.assignBookToUser(userVo, bookVo);
			restTemplate.put("http://BOOK-SERVICE/book/update", bookVo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.userGotBook);

	}

	private Boolean checkIfBookIsAlreadyAssigned(@Valid BookTransactionVO bookVO, UserVo userVo) {
		if (userVo.getBooks() == null) {
			return false;

		}

		for (BookVO book : userVo.getBooks()) {
			if (Objects.equals(book.getIsbn(), bookVO.getIsbn())) {
				return true;
			}
		}
		return false;
	}

	@PostMapping("/returnBookToLibrary")
	public ResponseEntity<?> returnBookToLibrary(@Valid @RequestBody BookTransactionVO bookVO) throws Exception {

		try {
			UserVo userVo = userService.findById(bookVO.getUserId());
			if (userVo == null) {
				return commonMethods.errorMsg(MessageConstant.userNotFound);
			}

			ResponseEntity<BookVO> res = restTemplate
					.getForEntity("http://BOOK-SERVICE/book/getById?isdnNo=" + bookVO.getIsbn(), BookVO.class);
			BookVO bookVo = res.getBody();

			if (bookVo == null || bookVo.getBookId() == null) {
				return commonMethods.errorMsg(MessageConstant.bookNotOfLibrary);
			}

			bookVo.setAvailableCopies(bookVo.getAvailableCopies() + 1);
			userService.unAssignBookToUser(userVo, bookVo);
			restTemplate.put("http://BOOK-SERVICE/book/update", bookVo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.userreturnBook);

	}

}
