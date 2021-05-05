package com.notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.beans.Note;
import com.notes.beans.User;
import com.notes.repo.NoteRepository;
import com.notes.repo.UserRepository;

@Service
public class NotesService {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private UserRepository userRepository;

	public List<Note> getUserNotes(long userId) {
		return this.userRepository.findById(userId).get().getNotes();
	}

	public Note addUserNote(User currentUser, Note note) {
		note.setUser(currentUser);
		if (this.noteRepository.existsById(note.getId())) {
			throw new RuntimeException("Note with the same id already exists");
		}
		this.noteRepository.save(note);
		return note;
	}

	public Note updateNote(User currentUser, Note updatedNote) {
		Note noteToUpdate = this.noteRepository.findById(updatedNote.getId()).get();
		if (noteToUpdate.getUser().getId() != currentUser.getId()) {
			throw new RuntimeException("The note does not belong to the authenticated user.");
		}
		noteToUpdate.updateNote(updatedNote);
		this.noteRepository.save(noteToUpdate);
		return noteToUpdate;
	}

	public Note deleteNote(User currentUser, Note note) {
		Note noteToDelete = this.noteRepository.findById(note.getId()).get();
		if (noteToDelete.getUser().getId() != currentUser.getId()) {
			throw new RuntimeException("The note does not belong to the authenticated user.");
		}
		this.noteRepository.deleteById(noteToDelete.getId());
		return noteToDelete;

	}
}
