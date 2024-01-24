package com.adda.app.Service;

import java.util.Set;

import com.adda.app.Exam.Question;
import com.adda.app.Exam.Quiz;

public interface IQuestionService {

	
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public Set<Question> getQuestion();
	public Question getQuestion(Long qid);
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	public void deleteQuestion(Long qid);
	public Question get(Long questionId);
}
