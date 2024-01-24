package com.adda.app.Controller;

import javax.websocket.server.PathParam;

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
import com.adda.app.Service.IcategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	private IcategoryService cService;
	
	
	//add Category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)
	{
		return new ResponseEntity<Category>(cService.addCategory(category),HttpStatus.CREATED);
	}
	
	//get Category
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") Long catid)
	{
		return new ResponseEntity<Category>(cService.getCategory(catid),HttpStatus.OK);
	}
	//get all categories
	@GetMapping("/")
	public ResponseEntity<?> getAllCategory()
	{
		return ResponseEntity.ok(this.cService.getCategories());
	}
	
	//update category
	@PutMapping("/")
	public Category updatecategory(@RequestBody Category category) 
	{
		return this.cService.updateCategory(category);
	}
	
  //delete category
	@DeleteMapping("/{id}")
	public void deletecategory(@PathVariable("id") Long id) 
	{
		this.cService.deleteCategory(id);
	}
	
	
}
