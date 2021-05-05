package com.notes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.beans.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
