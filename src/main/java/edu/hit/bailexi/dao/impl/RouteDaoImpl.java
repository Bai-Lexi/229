package edu.hit.bailexi.dao.impl;

import edu.hit.bailexi.dao.RouteDao;
import edu.hit.bailexi.domain.Route;
import edu.hit.bailexi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {
        String sql="select count(*) from tab_route ";
        StringBuilder sb=new StringBuilder(sql);
        Integer result;

        if(cid!=0){
            sb.append(" where cid = ? ");
            sql=sb.toString();

            if ((result = template.queryForObject(sql, Integer.class, cid)) != null) {
                return result;
            }
        }
        if(!rname.equals("null") && rname.length()>0){
            sb.append(" where rname like ? ");
            rname = "%"+rname+"%";
            sql=sb.toString();
            if ((result = template.queryForObject(sql, Integer.class, rname)) != null) {
                return result;
            }
        }

        return 0;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";
        //定义sql模板
        String sql="select * from tab_route ";
        StringBuilder sb=new StringBuilder(sql);

        List<Object> params=new ArrayList<>();
        if(cid!=0){
            sb.append(" where cid = ? ");
            params.add(cid);
        }
        if(!rname.equals("null") && rname.length()>0){
            sb.append(" where rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");//分页条件
        params.add(start);
        params.add(pageSize);
        sql=sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class),rid);
    }

    @Override
    public void updateFavoriteCount(String rid,Integer count) {
        String sql = "update tab_route set count= ? where rid=? ";

        template.update(sql,count,rid);
    }
}
