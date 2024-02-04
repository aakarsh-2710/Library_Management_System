package com.tata.UserService.vo;

public class BookVO {

	private Integer bookId;
	private Integer isbn;
	private String title;
	private String author;
	private Integer totalCopies;
	private Integer availableCopies;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}

	public BookVO() {
		super();
	}

	public BookVO(Integer bookId, Integer isbn, String title, String author, Integer totalCopies,
			Integer availableCopies) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
	}

	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", totalCopies=" + totalCopies + ", availableCopies=" + availableCopies + "]";
	}

}
