package com.adda.app.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adda.app.Exam.Question;
import com.adda.app.Exam.Quiz;
import com.adda.app.Service.IQuestionService;
import com.adda.app.Service.IQuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private IQuestionService QueService;
	@Autowired
	private IQuizService quizService;
	//add Question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		return new ResponseEntity<Question>(this.QueService.addQuestion(question),HttpStatus.CREATED);
	}
	
	//update question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
	{
		return new ResponseEntity<Question>(this.QueService.addQuestion(question),HttpStatus.CREATED);
	}
	
	//get Question of any quiz
	@GetMapping("/quiz/{quizid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quizid") Long quizid)
	{
//		Quiz q = new Quiz();
//		q.setQuizId(quizid);
//		Set<Question> questionofQuiz=this.QueService.getQuestionsOfQuiz(q);
//		return ResponseEntity.ok(questionofQuiz);
		Quiz q=this.quizService.getQuiz(quizid);
		Set<Question> question = q.getQuestions();
		List<Question>list = new ArrayList(question);
		if(list.size()>Integer.parseInt(q.getNoOfQuestion())) 
		
		{
			list=list.subList(0, Integer.parseInt(q.getNoOfQuestion()+1));
		}
		list.forEach((q1)->{
			q1
			.setAnswer("");
			});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/quiz/all/{quizid}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("quizid") Long quizid)
	{
		Quiz q = new Quiz();
		q.setQuizId(quizid);
		Set<Question> questionofQuiz=this.QueService.getQuestionsOfQuiz(q);
		return ResponseEntity.ok(questionofQuiz);
		
			}
	
	//get Single Question
	@GetMapping("/{questionid}")
	public Question getQuestion(@PathVariable("questionid") Long Qid) 
	{
		return this.QueService.getQuestion(Qid);
	}
	
	//delete qquestion
	@DeleteMapping("/{quesid}")
	public void delete(@PathVariable("quesid") Long quesid) 
	{
		this.QueService.deleteQuestion(quesid);
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{
	
		double marksGot=0;
		int correctAnswer=0;
		int attempted=0;
		for(Question q:questions)
		{
		Question que= this.QueService.get(q.getQuestionId());
		if(que.getAnswer().trim().equals(q.getGivenAnswer())) 
		{
			//Correct Answer
			correctAnswer++;
			double markSingle =Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
			marksGot += markSingle;		;
		}
		if(q.getGivenAnswer()!=null) 
		{
			attempted++;
		}
		}
		Map<Object,Object> map = Map.of("marksGot" ,marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
	
	
}
