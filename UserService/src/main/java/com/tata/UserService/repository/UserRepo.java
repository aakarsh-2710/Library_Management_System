package com.tata.UserService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tata.Entity.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query(value = "select * from users where emailId =:emailId", nativeQuery = true)
	Optional<User> findByEmail(String emailId);

}
