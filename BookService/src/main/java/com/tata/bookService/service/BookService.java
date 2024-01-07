package com.tata.bookService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tata.Entity.Book;
import com.tata.bookService.builder.BookBuilder;
import com.tata.bookService.repository.BookRepo;
import com.tata.bookService.vo.BookVO;

@Service
public class BookService {

	private BookRepo bookRepo;

	private BookBuilder bookBuilder;

	@Autowired
	public BookService(BookRepo bookRepo, BookBuilder bookBuilder) {
		super();
		this.bookRepo = bookRepo;
		this.bookBuilder = bookBuilder;
	}

	public List<BookVO> findAllBooks() {

		List<Book> book = bookRepo.findAll();
		return bookBuilder.convertBookListToBookVoList(book);

	}

	public BookVO findByIsbnNo(Integer isdnNo) {
		Optional<Book> optBook = bookRepo.findByIsbnNo(isdnNo);
		if (optBook.isPresent()) {
			Book book = optBook.get();
			return bookBuilder.convertBookToBookVo(book);

		}
		return null;
	}

	public void saveBook(BookVO bookVo) {

		Book book = bookBuilder.convertBookVoToBook(bookVo);
		bookRepo.save(book);

	}

	public void deleteBookById(Integer isdnNo) {
		bookRepo.deleteByIsbnNo(isdnNo);

	}

	public Boolean checkIfBookAlreadyPresent(Integer isbn) {

		if (isbn == null) {
			return false;
		} else {
			Optional<Book> book = bookRepo.findByIsbnNo(isbn);
			if (book.isPresent()) {
				return true;
			}
		}

		return false;
	}

}
