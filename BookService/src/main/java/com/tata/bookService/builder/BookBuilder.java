package com.tata.bookService.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tata.Entity.Book;
import com.tata.bookService.vo.BookVO;

@Component
public class BookBuilder {

	public List<BookVO> convertBookListToBookVoList(List<Book> bookList) {

		List<BookVO> bookVOList = new ArrayList<>();
		for (Book book : bookList) {
			BookVO bookVO = new BookVO();
			bookVO.setAuthor(book.getAuthor());
			bookVO.setAvailableCopies(book.getAvailableCopies());
			bookVO.setBookId(book.getBookId());
			bookVO.setIsbn(book.getIsbn());
			bookVO.setTitle(book.getTitle());
			bookVO.setTotalCopies(book.getTotalCopies());

			bookVOList.add(bookVO);
		}

		return bookVOList;
	}

	public BookVO convertBookToBookVo(Book book) {
		BookVO bookVO = new BookVO();
		bookVO.setAuthor(book.getAuthor());
		bookVO.setAvailableCopies(book.getAvailableCopies());
		bookVO.setBookId(book.getBookId());
		bookVO.setIsbn(book.getIsbn());
		bookVO.setTitle(book.getTitle());
		bookVO.setTotalCopies(book.getTotalCopies());

		return bookVO;
	}

	public Book convertBookVoToBook(BookVO bookVo) {

		Book book = new Book();
		book.setAuthor(bookVo.getAuthor());
		book.setAvailableCopies(bookVo.getAvailableCopies());
		book.setBookId(bookVo.getBookId());
		book.setIsbn(bookVo.getIsbn());
		book.setTitle(bookVo.getTitle());
		book.setTotalCopies(bookVo.getTotalCopies());
		return book;
	}

}
