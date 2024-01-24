package com.adda.app.Reposatotries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adda.app.entity.User;

public interface IUserReposatory extends JpaRepository<User, Long> {

	User findByusername(String username);
}
