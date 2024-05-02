package com.tata.UserService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tata.UserService.vo.BookVO;

@FeignClient(name = "BOOK-SERVICE")
public interface BookService {

	@PutMapping("/book/update")
	ResponseEntity<?> updateBook(@RequestBody BookVO book);

	@GetMapping("/book/getById")
	ResponseEntity<BookVO> getBook(@RequestParam Integer isdnNo);
}
