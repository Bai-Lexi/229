package edu.hit.chaijiarui.service.impl;

import edu.hit.chaijiarui.dao.RouteDao;
import edu.hit.chaijiarui.dao.impl.RouteDaoImpl;
import edu.hit.chaijiarui.domain.PageBean;
import edu.hit.chaijiarui.domain.Route;
import edu.hit.chaijiarui.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = (RouteDao) new RouteDaoImpl();

    @Override
    public PageBean<Route> PageQuery(int cid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页的显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }

}
