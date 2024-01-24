package com.adda.app.ServiceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adda.app.Exam.Category;
import com.adda.app.Exam.Quiz;
import com.adda.app.Reposatotries.IQuizRepo;
import com.adda.app.Service.IQuizService;
@Service
public class QuizServiceImpl implements IQuizService {
    @Autowired
	private IQuizRepo qrepo;
	@Override
	public Quiz AddQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.qrepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.qrepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizes() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.qrepo.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.qrepo.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub
		Quiz quiz = new Quiz();
		quiz.setQuizId(quizId);
		this.qrepo.delete(quiz);
	}

	@Override
	public List<Quiz> getQuizzessOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.qrepo.findBycategory(c);
	}
	//get Active Quizzess

	@Override
	public List<Quiz> getActiveQuizzess() {
		// TODO Auto-generated method stub
		return this.qrepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzessOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.qrepo.findByCategoryAndActive(c, true);
	}
	
	
	

}
