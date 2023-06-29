package edu.hit.bailexi.dao.impl;

import edu.hit.bailexi.dao.RouteDao;
import edu.hit.bailexi.domain.Route;
import edu.hit.bailexi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {
        String sql="select count(*) from tab_route ";
        StringBuilder sb=new StringBuilder(sql);

        List params=new ArrayList();
        if(cid!=0){
            sb.append(" where cid = ? ");
            sql=sb.toString();
            return template.queryForObject(sql, Integer.class, cid);
        }
        if(!rname.equals("null") && rname.length()>0){
            sb.append(" where rname like ? ");
            rname = "%"+rname+"%";
            sql=sb.toString();
            return template.queryForObject(sql, Integer.class, rname);
        }

        return 0;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";
        //定义sql模板
        String sql="select * from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        List params=new ArrayList();
        if(cid!=0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(!rname.equals("null") && rname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");//分页条件
        params.add(start);
        params.add(pageSize);
        sql=sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }
}
