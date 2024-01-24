package com.adda.app.Reposatotries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adda.app.Exam.Category;

public interface ICategoryRepo extends JpaRepository<Category, Long> {

}
