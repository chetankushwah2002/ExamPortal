package com.adda.app.Controller;

import java.util.List;

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

import com.adda.app.Exam.Category;
import com.adda.app.Exam.Quiz;
import com.adda.app.Service.IQuizService;
@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
	private IQuizService Qservice;
    
    //add quiz
    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
    {
		return new ResponseEntity<Quiz>(this.Qservice.AddQuiz(quiz),HttpStatus.CREATED);
    	
    }
    
    //update quiz
    @PutMapping
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz)
    {
    	return ResponseEntity.ok(this.Qservice.updateQuiz(quiz));
    }
    //get all quiz
    @GetMapping("/")
    public ResponseEntity<?> getAllQuiz()
    {
    	return  ResponseEntity.ok(Qservice.getQuizes());
    }
    //get one quiz
    @GetMapping("/{qid}")
    public ResponseEntity<?> getQuiz(@PathVariable("qid") Long qid)
    {
    	return  ResponseEntity.ok(Qservice.getQuiz(qid));
    }
    //delete Quiz
    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid)
    {
    	this.Qservice.deleteQuiz(qid);
    }
     
    @GetMapping("/category/{cid}")
	public List<Quiz>getQuizzesOfCategory(@PathVariable("cid") Long cid)
	{
    	Category c =new Category();
    	c.setCatId(cid);
    	return this.Qservice.getQuizzessOfCategory(c);
	}
    
    //get active Quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzess()
    {
    	return this.Qservice.getActiveQuizzess();
    }
    
    //get active Quizzes
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzessOfCategory(@PathVariable("cid") Long cid)
    {
    	Category category = new Category();
    	category.setCatId(cid);
    	return this.Qservice.getActiveQuizzessOfCategory(category);
    }
    
    
}
