package com.tata.UserService.constant;

public class MessageConstant {

	private MessageConstant() {
		throw new IllegalStateException("Utility class");
	}

	public static final String technicalIssueOccured = "Technical issue Occured. Please contact admin";
	public static final String bookAddedSucceddfully = "Book added Successfully";
	public static final String bookNotFound = "Book Not available in Library.";
	public static final String bookDeletedSucceddfully = "Book deleted Successfully";
	public static final String bookUpdatedSucceddfully = "Book updated Successfully";
	public static final String userNotFound = "Unable to find User. Please provide valid user id ";
	public static final String userAddedSucceddfully = "User added Successfully";
	public static final String userDeletedSucceddfully = "User deleted Successfully";
	public static final String userUpdatedSucceddfully = "User updated Successfully";
	public static final String userAlreadyPtesent = "User with given mail id is already present.";
	public static final String userIdNotFound = "Please Provide userId you wish to update";
	public static final String exception_handled = "Exception Handled";
	public static final String created_successfully = "Created Successfully";
	public static final String error_occurred = "Error Occurred";
	public static final Integer exception_handled_code = 329;
	public static final Integer error_occurred_code = 332;
	public static final Integer created_successfully_code = 221;
	public static final String valid_phone_no = " must be a 10-digit number";
	public static final String userGotBook = "Book has been assigned to user";
	public static final String userreturnBook = "Book has been returned by user";
	public static final String bookNotOfLibrary = "This is not the book of our Library";
	public static final String bookAlreadyAssigned = "This Book is already assigned to the given user";

}
