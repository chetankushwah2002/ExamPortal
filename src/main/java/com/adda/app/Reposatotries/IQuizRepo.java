package com.adda.app.Reposatotries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adda.app.Exam.Category;
import com.adda.app.Exam.Quiz;

public interface IQuizRepo extends JpaRepository<Quiz, Long> {

	public List<Quiz> findBycategory(Category c);
	@Query("SELECT Q From Quiz Q WHERE Q.active=1 ")
	public List<Quiz> getCategoryIsActive();
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c,Boolean b);
}
