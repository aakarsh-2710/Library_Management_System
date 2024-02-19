package com.tata.UserService.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.tata.UserService.CommonMethodsTest;
import com.tata.UserService.constant.MessageConstant;
import com.tata.UserService.exception.GlobalExceptionHandler;
import com.tata.UserService.service.UserService;
import com.tata.UserService.util.CommonMethods;
import com.tata.UserService.vo.UserVo;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private CommonMethods commonMethods;

	@InjectMocks
	private GlobalExceptionHandler exceptionHandler;

	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService, restTemplate, commonMethods)).build();
	}

	@Test
	void testGetAllUsers_Successful() throws Exception {
		// Mocking the service response
		List<UserVo> users = new ArrayList<>();
		UserVo userVO = CommonMethodsTest.createUserVO(1, "John Doe", 1234567890L, "john.doe@example.com");

		users.add(userVO);
		when(userService.findAllUsers()).thenReturn(users);

		// Perform the request and validate the result
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(users.size()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(userVO.getUserId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(userVO.getUserName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNo").value(userVO.getPhoneNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].emailId").value(userVO.getEmailId()));

		// Verify interactions
		verify(userService, times(1)).findAllUsers();
	}

	@Test
	void testGetAllUsers_EmptyList() throws Exception {
		// Mocking the service response with an empty list
		when(userService.findAllUsers()).thenReturn(Collections.emptyList());

		// Perform the request and validate the result
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));

		// Verify interactions
		verify(userService, times(1)).findAllUsers();
	}

	@Test
	void testGetAllUsers_NullList() throws Exception {
		// Mocking the service response with a null list
		when(userService.findAllUsers()).thenReturn(null);

		// Perform the request and validate the result
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());

		// Verify interactions
		verify(userService, times(1)).findAllUsers();
	}

	@Test
	void testGetAllUsersWithException() throws Exception {
		// Arrange: Mock the UserService to throw an exception
		Mockito.when(userService.findAllUsers()).thenThrow(new RuntimeException("Error Message"));

		// Act: Perform the request
		mockMvc.perform(get("/user/getAll"))
				// Assert: Expect the status code to be INTERNAL_SERVER_ERROR (500)
				.andExpect(status().isInternalServerError())
				// Assert: Expect the response body to have specific fields
				.andExpect(jsonPath("$.url").value("/user/getAll"))
				.andExpect(jsonPath("$.message").value(MessageConstant.technicalIssueOccured))
				.andExpect(jsonPath("$.error").value(MessageConstant.exception_handled))
				.andExpect(jsonPath("$.errorCode").value(MessageConstant.exception_handled_code))
				.andExpect(jsonPath("$.timestamp").exists());
	}

}
