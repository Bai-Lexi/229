package edu.hit.chaijiarui.dao;

import edu.hit.chaijiarui.domain.Route;

import java.util.List;

public interface RouteDao {

    //根据cid查询总记录数
    public int findTotalCount(int cid);

    //根据cid，start，pageSize查询当前页数据集合
    public List<Route> findByPage(int cid, int start, int pageSize);
}
