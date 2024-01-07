package com.tata.bookService.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookVO {

	private Integer bookId;
	@NotNull
	private Integer isbn;
	@NotBlank
	private String title;
	@NotBlank
	private String author;
	@NotNull
	private Integer totalCopies;
	@NotNull
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

}
