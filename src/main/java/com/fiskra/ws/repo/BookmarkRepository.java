package com.fiskra.ws.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiskra.ws.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	List<Bookmark> findByUserId(Long id);
	
	Bookmark findByUserIdAndId(Long userId, Long id);
}
