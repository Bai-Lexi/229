package edu.hit.bailexi.service;

import edu.hit.bailexi.domain.PageBean;
import edu.hit.bailexi.domain.Route;

public interface RouteService {
    PageBean<Route> PageQuery(int cid, int currentPage, int pageSize, String rname);

}
