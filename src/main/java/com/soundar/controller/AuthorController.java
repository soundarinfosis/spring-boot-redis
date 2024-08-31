package com.soundar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soundar.entity.Author;
import com.soundar.service.AuthorServices;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
	
	@Autowired
	private AuthorServices authorServices;
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getUserById(@PathVariable int id){		
		Author author = authorServices.getAuthorById(id);
		return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author){		
		Author saveAuthor = authorServices.saveAuthor(author);		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveAuthor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable int id){
		authorServices.deleteAuthor(id);
		return ResponseEntity.noContent().build();
		
	}

}
