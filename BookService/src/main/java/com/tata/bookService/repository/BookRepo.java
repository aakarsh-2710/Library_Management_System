package com.tata.bookService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tata.Entity.Book;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BookRepo extends JpaRepository<Book, Integer> {

	@Modifying
	@Query(value = "delete from Book where isbn =:isbn", nativeQuery = true)
	void deleteByIsbnNo(Integer isbn);

	@Query(value = "select * from Book where isbn =:isdnNo", nativeQuery = true)
	Optional<Book> findByIsbnNo(Integer isdnNo);

	@Query(value = "select * from Book where author =:author and title=:title", nativeQuery = true)
	Optional<Book> findByName(String author, String title);

}
