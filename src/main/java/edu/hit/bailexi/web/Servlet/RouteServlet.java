package edu.hit.bailexi.web.Servlet;

import edu.hit.bailexi.domain.PageBean;
import edu.hit.bailexi.domain.Route;
import edu.hit.bailexi.service.RouteService;
import edu.hit.bailexi.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service=new RouteServiceImpl();
    //分页查询
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        int cid =0;//类别id
        //处理参数
        if(cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;//当前页码，不传递默认为1
        if(currentPageStr != null && currentPageStr.length() >0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，不传递默认五条
        if(pageSizeStr != null && pageSizeStr.length() >0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        //调用service查询PageBean对象

        PageBean<Route> pageBean = service.PageQuery(cid,currentPage,pageSize);

        //将PageBean对象序列化为json返回
        writeValue(pageBean,response);
    }

}
