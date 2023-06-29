package edu.hit.bailexi.dao;

import edu.hit.bailexi.domain.Route;

import java.util.List;

public interface RouteDao {

    //根据cid查询总记录数
    public int findTotalCount(int cid,String rname);

    //根据cid，start，pageSize查询当前页数据集合
    public List<Route> findByPage(int cid, int start, int pageSize,String rname);

    /**
     * 根据id查询
     * @return rid
     */
    public Route findOne(int rid);
}
