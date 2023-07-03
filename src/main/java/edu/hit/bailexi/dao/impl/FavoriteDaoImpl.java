package edu.hit.bailexi.dao.impl;

import edu.hit.bailexi.dao.FavoriteDao;
import edu.hit.bailexi.domain.Favorite;
import edu.hit.bailexi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void add(String rid, int uid) {
        String sql ="insert into tab_favorite values(?,?,?)";

        template.update(sql,rid,new Date(),uid);

    }

    @Override
    public Favorite findFavouriteByRidAndUid(String rid, int uid) {
        String sql = "select * from tab_favorite where rid=? and uid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
    }
    //收藏量
    @Override
    public int findFavoriteCount(String rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        Integer result;
        if ((result = template.queryForObject(sql,Integer.class,rid)) != null) {
            return result;
        }
        return 0;
    }
}
