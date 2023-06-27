package edu.hit.bailexi.service.impl;

import edu.hit.bailexi.domain.Category;
import edu.hit.bailexi.dao.CategoryDao;
import edu.hit.bailexi.dao.impl.CategoryDaoImpl;
import edu.hit.bailexi.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() { return categoryDao.findAll();}
}
