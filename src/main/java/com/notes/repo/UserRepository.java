package com.notes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.beans.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
