package edu.hit.chaijiarui.dao.impl;

import edu.hit.chaijiarui.dao.CategoryDao;
import edu.hit.chaijiarui.util.JDBCUtils;
import edu.hit.chaijiarui.domain.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return  template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
