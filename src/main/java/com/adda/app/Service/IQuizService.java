package com.adda.app.Service;

import java.util.List;
import java.util.Set;

import com.adda.app.Exam.Category;
import com.adda.app.Exam.Quiz;

public interface IQuizService {

	public Quiz AddQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuizes();
	public Quiz getQuiz(Long quizId);
	public void deleteQuiz(Long quizId);
	public List<Quiz>getQuizzessOfCategory(Category c);
	public List<Quiz> getActiveQuizzess();
	public List<Quiz> getActiveQuizzessOfCategory(Category c);
	
}
