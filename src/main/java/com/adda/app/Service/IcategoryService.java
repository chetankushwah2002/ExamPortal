package com.adda.app.Service;

import java.util.Set;

import com.adda.app.Exam.Category;

public interface IcategoryService {

	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public Set<Category> getCategories();
	public Category getCategory(Long categoryId);
	public void deleteCategory(Long categoryId);
}
