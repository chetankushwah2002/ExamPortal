package com.adda.app.Reposatotries;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adda.app.Exam.Question;
import com.adda.app.Exam.Quiz;

public interface IQuestionRepo extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);

}
