package edu.hit.chaijiarui.service;

import edu.hit.chaijiarui.domain.PageBean;
import edu.hit.chaijiarui.domain.Route;

public interface RouteService {
    PageBean<Route> PageQuery(int cid, int currentPage, int pageSize);

}
