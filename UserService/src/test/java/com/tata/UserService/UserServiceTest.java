package com.tata.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.tata.Entity.User;
import com.tata.UserService.builder.UserBuilder;
import com.tata.UserService.repository.UserRepo;
import com.tata.UserService.service.UserService;
import com.tata.UserService.vo.UserVo;

@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepo userRepo;

	@Spy
	private UserBuilder userBuilder;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAllUsers() {
		// Mocking the repository response

		User user1 = CommonMethodsTest.createUser(1, "John Doe", 1234567890L, "john.doe@example.com");
		User user2 = CommonMethodsTest.createUser(2, "Jane Smith", 9876543210L, "jane.smith@example.com");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		when(userRepo.findAll()).thenReturn(users);
		// Call the service method
		assertEquals(2, userService.findAllUsers().size());
	}

	@Test
	void testFindAllUsers_EmptyList() {
		// Mocking an empty repository response
		when(userRepo.findAll()).thenReturn(Collections.emptyList());

		// Call the service method
		assertTrue(userService.findAllUsers().isEmpty());
	}

	@Test
	void testFindAllUsers_NullList() {
		when(userRepo.findAll()).thenReturn(null);
		assertTrue(userService.findAllUsers().isEmpty());
	}

	@Test
	void testFindAllUsers_UserProperties() {
		User user1 = CommonMethodsTest.createUser(1, "John Doe", 1234567890L, "john.doe@example.com");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		when(userRepo.findAll()).thenReturn(users);

		List<UserVo> userVos = userService.findAllUsers();
		assertEquals(1, userVos.size());

		UserVo userVo = userVos.get(0);
		User user = users.get(0);
		assertEquals(user.getUserId(), userVo.getUserId());
		assertEquals(user.getUserName(), userVo.getUserName());
		assertEquals(user.getPhoneNo(), userVo.getPhoneNo());
		assertEquals(user.getEmailId(), userVo.getEmailId());
	}

	@Test
	void testFindById_UserFound() {
		// Mocking the repository response for a user found
		User user = CommonMethodsTest.createUser(1, "John Doe", 1234567890L, "john.doe@example.com");
		when(userRepo.findById(1)).thenReturn(Optional.of(user));

		// Call the service method
		UserVo result = userService.findById(1);

		// Verify the result and interactions
		assertNotNull(result);

	}

	@Test
	void testFindById_UserNotFound() {
		// Mocking the repository response for a user not found
		when(userRepo.findById(1)).thenReturn(Optional.empty());

		// Call the service method
		UserVo result = userService.findById(1);

		// Verify the result and interactions
		assertNull(result);

	}

	@Test
	void testFindById_NullId() {
		// Call the service method with null ID
		assertThrows(IllegalArgumentException.class, () -> userService.findById(null));
	}

	@Test
	void testSaveUser_successfulSave() {
		// Mocking the builder response
		UserVo userVO = CommonMethodsTest.createUserVO(1, "John Doe", 1234567890L, "john.doe@example.com");

		// Call the service method
		User user = userService.saveUser(userVO);

		// Verifying Properties

		assertEquals(user.getUserId(), userVO.getUserId());
		assertEquals(user.getUserName(), userVO.getUserName());
		assertEquals(user.getPhoneNo(), userVO.getPhoneNo());
		assertEquals(user.getEmailId(), userVO.getEmailId());

		// Verify interactions
		verify(userBuilder, times(1)).convertUserVoToUser(userVO);
		verify(userRepo, times(1)).save(user);
	}

	@Test
	void testSaveUser_NullUserVo() {
		// Call the service method with null UserVo
		assertThrows(IllegalArgumentException.class, () -> userService.saveUser(null));

		// Verify that save method is not called when UserVo is null
		verify(userBuilder, never()).convertUserVoToUser(any());
		verify(userRepo, never()).save(any());
	}

	@Test
	void testDeleteById_SuccessfulDelete() {
		// Call the service method
		userService.deleteById(1);

		// Verify interactions
		verify(userRepo, times(1)).deleteById(1);
	}

	@Test
	void testDeleteById_NullId() {
		// Call the service method with null ID
		assertThrows(IllegalArgumentException.class, () -> userService.deleteById(null));

		// Verify that deleteById method is not called when ID is null
		verify(userRepo, never()).deleteById(any());
	}

}
