package com.fiskra.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiskra.ws.model.Bookmark;
import com.fiskra.ws.model.BookmarkResource;
import com.fiskra.ws.repo.BookmarkRepository;

@RestController
public class BookmarkController {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@RequestMapping(method = RequestMethod.GET, path="/bookmark/{id}")
	BookmarkResource readBookmarks(@PathVariable Long id) {
		Bookmark b = bookmarkRepository.findOne(id);
		BookmarkResource br = new BookmarkResource();
		br.bookmark = b;
		br.add(new Link("http://localhost/bookmark/"+id));

		return br;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{userId}/bookmarks")
	Collection<Bookmark> readUserBookmarks(@PathVariable Long userId) {	
		return this.bookmarkRepository.findByUserId(userId);
	}

	

}
