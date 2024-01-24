package com.adda.app.ServiceImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adda.app.Exam.Question;
import com.adda.app.Exam.Quiz;
import com.adda.app.Reposatotries.IQuestionRepo;
import com.adda.app.Reposatotries.IQuizRepo;
import com.adda.app.Service.IQuestionService;
@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
	private IQuestionRepo Qesrepo;
	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.Qesrepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.Qesrepo.save(question);
	}

	@Override
	public Set<Question> getQuestion() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.Qesrepo.findAll());
	}

	@Override
	public Question getQuestion(Long qid) {
		// TODO Auto-generated method stub
		return this.Qesrepo.findById(qid).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.Qesrepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long qid) {
		// TODO Auto-generated method stub
		Question q = new Question();
		q.setQuestionId(qid);
		this.Qesrepo.delete(q);
	}

	@Override
	public Question get(Long questionId) {
		// TODO Auto-generated method stub
		return this.Qesrepo.getOne(questionId);
	}

}
