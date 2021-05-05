package com.notes.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="users")
public class User{


	@Id
    @GeneratedValue
    private long id;
	
	@Column(unique=true)
	@NonNull
	private String username;
	
	@Column(nullable = false)
	@NonNull
	@JsonIgnore
	private String password;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Note> notes;
}
