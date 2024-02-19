package com.tata.UserService.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.tata.Entity.Book;
import com.tata.Entity.User;
import com.tata.UserService.vo.BookVO;
import com.tata.UserService.vo.UserVo;

@Component
public class UserBuilder {

	public List<UserVo> convertUserListToUserVoList(List<User> users) {
		List<UserVo> list = new ArrayList<>();
		if (users != null) {
			for (User user : users) {
				UserVo userVo = new UserVo();
				userVo.setEmailId(user.getEmailId());
				userVo.setPhoneNo(user.getPhoneNo());
				userVo.setUserId(user.getUserId());
				userVo.setUserName(user.getUserName());
				userVo.setBooks(getSetOfBookVo(user.getBooks()));
				list.add(userVo);
			}
		}
		return list;
	}

	private Set<BookVO> getSetOfBookVo(Set<Book> books) {
		Set<BookVO> bookVOs = new HashSet<>();

		for (Book book : books) {
			BookVO bookVO = new BookVO();
			bookVO.setAuthor(book.getAuthor());
			bookVO.setAvailableCopies(book.getAvailableCopies());
			bookVO.setBookId(book.getBookId());
			bookVO.setIsbn(book.getIsbn());
			bookVO.setTitle(book.getTitle());
			bookVO.setTotalCopies(book.getTotalCopies());

			bookVOs.add(bookVO);

		}

		return bookVOs;
	}

	private Set<Book> getSetOfBook(Set<BookVO> books) {
		Set<Book> bookset = new HashSet<>();

		for (BookVO bookVo : books) {
			Book book = new Book();
			book.setAuthor(bookVo.getAuthor());
			book.setAvailableCopies(bookVo.getAvailableCopies());
			book.setBookId(bookVo.getBookId());
			book.setIsbn(bookVo.getIsbn());
			book.setTitle(bookVo.getTitle());
			book.setTotalCopies(bookVo.getTotalCopies());

			bookset.add(book);

		}

		return bookset;
	}

	public UserVo convertUserToUserVo(User user) {
		UserVo userVo = new UserVo();
		userVo.setEmailId(user.getEmailId());
		userVo.setPhoneNo(user.getPhoneNo());
		userVo.setUserId(user.getUserId());
		userVo.setUserName(user.getUserName());
		if (user.getBooks() != null)
			userVo.setBooks(getSetOfBookVo(user.getBooks()));
		return userVo;

	}

	public User convertUserVoToUser(UserVo userVo) {

		User user = new User();
		user.setEmailId(userVo.getEmailId());
		user.setPhoneNo(userVo.getPhoneNo());
		user.setUserId(userVo.getUserId());
		user.setUserName(userVo.getUserName());
		if (userVo.getBooks() != null)
			user.setBooks(getSetOfBook(userVo.getBooks()));
		return user;
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
