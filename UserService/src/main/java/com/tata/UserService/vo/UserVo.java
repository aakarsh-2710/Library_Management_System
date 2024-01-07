package com.tata.UserService.vo;

import java.util.Set;

import com.tata.UserService.constant.MessageConstant;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserVo {

	private Integer userId;
	@NotBlank
	private String userName;

	@Digits(integer = 10, fraction = 0, message = MessageConstant.valid_phone_no)
	private Long phoneNo;

	@Email
	private String emailId;

	private Set<BookVO> books;

	public Set<BookVO> getBooks() {
		return books;
	}

	public void setBooks(Set<BookVO> books) {
		this.books = books;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userName=" + userName + ", phoneNo=" + phoneNo + ", emailId=" + emailId
				+ ", books=" + books + "]";
	}

}
