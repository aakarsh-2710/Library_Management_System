package com.tata.bookService.controller;

import java.util.List;

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

import com.tata.bookService.constant.MessageConstant;
import com.tata.bookService.service.BookService;
import com.tata.bookService.util.CommonMethods;
import com.tata.bookService.vo.BookVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	private BookService bookService;

	private CommonMethods commonMethods;

	@Autowired
	public BookController(BookService bookService, CommonMethods commonMethods) {
		super();
		this.bookService = bookService;
		this.commonMethods = commonMethods;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBooks() throws Exception {
		List<BookVO> books = null;
		try {
			books = bookService.findAllBooks();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return new ResponseEntity<>(books, HttpStatus.FOUND);
	}

	@GetMapping("/getById")
	public ResponseEntity<?> getBook(@RequestParam Integer isdnNo) throws Exception {
		BookVO book = null;
		try {
			book = bookService.findByIsbnNo(isdnNo);
			if (book == null) {
				return commonMethods.errorMsg(MessageConstant.bookNotFound);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return new ResponseEntity<>(book, HttpStatus.FOUND);

	}

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@Valid @RequestBody BookVO book) throws Exception {
		try {
			Boolean isPresent = bookService.checkIfBookAlreadyPresent(book.getIsbn());
			if (isPresent) {
				return commonMethods.errorMsg(MessageConstant.bookAlreadyPresent);
			}
			bookService.saveBook(book);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.bookAddedSucceddfully);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBook(@RequestParam Integer isdnNo) throws Exception {
		try {
			bookService.deleteBookById(isdnNo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.bookDeletedSucceddfully);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateBook(@Valid @RequestBody BookVO book) throws Exception {

		try {
			if (book.getBookId() == null) {
				return commonMethods.errorMsg(MessageConstant.bookIdNotFound);
			}
			bookService.saveBook(book);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return commonMethods.successMsg(MessageConstant.bookUpdatedSucceddfully);
	}

}
