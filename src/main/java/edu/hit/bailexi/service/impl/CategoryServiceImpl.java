package edu.hit.chaijiarui.service.impl;

import edu.hit.chaijiarui.domain.Category;
import edu.hit.chaijiarui.dao.CategoryDao;
import edu.hit.chaijiarui.dao.impl.CategoryDaoImpl;
import edu.hit.chaijiarui.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() { return categoryDao.findAll();}
}
