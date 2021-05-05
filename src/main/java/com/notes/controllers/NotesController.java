package com.notes.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.beans.CustomUserDetails;
import com.notes.beans.Note;
import com.notes.beans.User;
import com.notes.services.NotesService;

@CrossOrigin
@RestController
@RequestMapping("notes")
public class NotesController {

	
	@Autowired
	private NotesService notesService;

	@GetMapping()
	public ResponseEntity<?> getNotes(Authentication authentication) {
		long userId = getCurrentUser(authentication).getId();
		return ResponseEntity.ok(this.notesService.getUserNotes(userId));
	}
	

	@PostMapping()
	public ResponseEntity<?> addNote(@Valid @RequestBody Note note,Authentication authentication) {
		User user = getCurrentUser(authentication);
		
		return ResponseEntity.ok(this.notesService.addUserNote(user,note));
	}
	
	@PutMapping()
	public ResponseEntity<?> updateNote(@Valid @RequestBody Note updatedNote,Authentication authentication) {
		User user = getCurrentUser(authentication);
		return ResponseEntity.ok(this.notesService.updateNote(user,updatedNote));
	}
	
	@DeleteMapping()
	public ResponseEntity<?> deleteNote(@Valid @RequestBody Note deleteNote,Authentication authentication) {
		User user = getCurrentUser(authentication);
		return ResponseEntity.ok(this.notesService.deleteNote(user,deleteNote));
	}
	
	private User getCurrentUser(Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		return userDetails.getUser();
	}
	
	
}
