package com.tata.UserService.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tata.Entity.Book;
import com.tata.Entity.User;
import com.tata.UserService.builder.UserBuilder;
import com.tata.UserService.repository.UserRepo;
import com.tata.UserService.vo.BookVO;
import com.tata.UserService.vo.UserVo;

@Service
public class UserService {

	private UserRepo userRepo;

	private UserBuilder userBuilder;

	@Autowired
	public UserService(UserRepo userRepo, UserBuilder userBuilder) {
		super();
		this.userRepo = userRepo;
		this.userBuilder = userBuilder;
	}

	public List<UserVo> findAllUsers() {
		List<User> users = userRepo.findAll();
		return userBuilder.convertUserListToUserVoList(users);
	}

	public UserVo findById(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return null;
		}
		return userBuilder.convertUserToUserVo(user.get());
	}

	public void saveUser(UserVo userVo) {
		User user = userBuilder.convertUserVoToUser(userVo);
		userRepo.save(user);
	}

	public Boolean checkIfEmailExists(String emailId) {

		if (emailId == null || emailId.isEmpty()) {
			return false;
		}

		Optional<User> user = userRepo.findByEmail(emailId);
		if (user.isPresent()) {
			return true;
		}
		return false;
	}

	public void deleteById(Integer userId) {
		userRepo.deleteById(userId);

	}

	public void assignBookToUser(UserVo userVo, BookVO bookVo) {
		User user = userBuilder.convertUserVoToUser(userVo);
		Book book = userBuilder.convertBookVoToBook(bookVo);
		user.getBooks().add(book);
		userRepo.save(user);
	}

	public void unAssignBookToUser(UserVo userVo, BookVO bookVo) {
		User user = userBuilder.convertUserVoToUser(userVo);
		Book book = userBuilder.convertBookVoToBook(bookVo);

		for (Book bk : user.getBooks()) {

			if (Objects.equals(bk.getIsbn(), book.getIsbn())) {
				user.getBooks().remove(bk);
				break;
			}
		}

		userRepo.save(user);
	}

}
