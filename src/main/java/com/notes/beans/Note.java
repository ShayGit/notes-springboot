package com.notes.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="notes")
public class Note {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NonNull
	@NotNull
	@NotBlank(message = "Please add note title.")
	private String title;
	
	private String body;
	
	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	private int priority;
	
	private boolean isRead;
	
	private String color;
	
	private String icon;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	private User user;
	
	public void updateNote(Note note) {
		this.title = note.getTitle();
		this.body = note.getBody();
		this.priority = note.getPriority();
		this.isRead = note.isRead();
		this.color = note.getColor();
		this.icon = note.getIcon();
	}
}
