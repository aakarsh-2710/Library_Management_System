package com.tata.bookService.constant;

public class MessageConstant {

	private MessageConstant() {
		throw new IllegalStateException("Utility class");
	}

	public static final String technicalIssueOccured = "Technical issue Occured. Please contact admin";
	public static final String bookAddedSucceddfully = "Book added Successfully";
	public static final String bookNotFound = "Unable to find Book. Please provide valid book id ";
	public static final String bookDeletedSucceddfully = "Book deleted Successfully";
	public static final String bookUpdatedSucceddfully = "Book updated Successfully";
	public static final String bookAlreadyPresent = "Book Already Present. Please check ISBN No";
	public static final String bookIdNotFound = "Please Provide bookId you wish to update";
	public static final String exception_handled = "Exception Handled";
	public static final Integer exception_handled_code = 329;
	public static final Integer created_successfully_code = 221;
	public static final Integer error_occurred_code = 332;
	public static final String created_successfully = "Created Successfully";
	public static final String error_occurred = "Error Occurred";

}
