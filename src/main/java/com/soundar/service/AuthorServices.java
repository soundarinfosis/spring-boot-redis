package com.soundar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.soundar.entity.Author;
import com.soundar.repository.AuthorRepository;

@Service
public class AuthorServices {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Cacheable(value = "authors", key = "#id")
	public Author getAuthorById(int id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	@CachePut(value = "authors", key="#author.author_id")
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	@CacheEvict(value = "authors", key="#id")
	public void deleteAuthor(int id) {
		authorRepository.deleteById(id);
	}

}
