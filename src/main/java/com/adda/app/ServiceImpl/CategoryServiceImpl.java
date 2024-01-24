package com.adda.app.ServiceImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adda.app.Exam.Category;
import com.adda.app.Reposatotries.ICategoryRepo;
import com.adda.app.Service.IcategoryService;
@Service
public class CategoryServiceImpl implements IcategoryService {
    @Autowired
	private ICategoryRepo crepo;
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return this.crepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return this.crepo.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.crepo.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return this.crepo.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		// TODO Auto-generated method stub
		Category c = new Category();
		c.setCatId(categoryId);
		this.crepo.delete(c);
	}

}
