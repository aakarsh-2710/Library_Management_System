package com.tata.UserService.vo;

import jakarta.validation.constraints.NotNull;

public class BookTransactionVO {

	@NotNull
	private Integer userId;
	@NotNull
	private Integer isbn;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "BookTransactionVO [userId=" + userId + ", isbn=" + isbn + "]";
	}

}
