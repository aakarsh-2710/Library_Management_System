package com.tata.UserService;

import java.util.HashSet;
import java.util.Set;

import com.tata.Entity.Book;
import com.tata.Entity.User;
import com.tata.UserService.vo.BookVO;
import com.tata.UserService.vo.UserVo;

public class CommonMethodsTest {

	public static User createUser(Integer userId, String userName, Long phoneNo, String emailId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPhoneNo(phoneNo);
		user.setEmailId(emailId);

		// Adding some sample books to the user's set of books
		Set<Book> books = new HashSet<>();
		books.add(createBook(1, 123456, "Sample Book 1", "Author 1", 100, 50));
		books.add(createBook(2, 789012, "Sample Book 2", "Author 2", 150, 75));

		user.setBooks(books);

		return user;
	}

	public static UserVo createUserVO(Integer userId, String userName, Long phoneNo, String emailId) {
		UserVo user = new UserVo();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPhoneNo(phoneNo);
		user.setEmailId(emailId);

		// Adding some sample books to the user's set of books
		Set<BookVO> books = new HashSet<>();
		books.add(createBookVO(1, 123456, "Sample Book 1", "Author 1", 100, 50));
		books.add(createBookVO(2, 789012, "Sample Book 2", "Author 2", 150, 75));

		user.setBooks(books);

		return user;
	}

	public static Book createBook(Integer bookId, Integer isbn, String title, String author, Integer totalCopies,
			Integer availableCopies) {
		Book book = new Book();
		book.setBookId(bookId);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setTotalCopies(totalCopies);
		book.setAvailableCopies(availableCopies);

		return book;
	}

	public static BookVO createBookVO(Integer bookId, Integer isbn, String title, String author, Integer totalCopies,
			Integer availableCopies) {
		BookVO book = new BookVO();
		book.setBookId(bookId);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setTotalCopies(totalCopies);
		book.setAvailableCopies(availableCopies);

		return book;
	}
}
