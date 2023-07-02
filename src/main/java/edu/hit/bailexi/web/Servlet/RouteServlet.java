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

        //接收rname
        String rname=request.getParameter("rname");
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid =0;//类别id
        //处理参数
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
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
            pageSize = 6;
        }

        //调用service查询PageBean对象

        PageBean<Route> pageBean = service.PageQuery(cid,currentPage,pageSize,rname);

        //将PageBean对象序列化为json返回
        writeValue(pageBean,response);
    }


    /**
     * 根据id查询一个商品的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接受参数id

        String rid = request.getParameter("rid");
        //2.调用service查询route对象
        Route route = service.findOne(rid);
        //3.转为json写回客户端
        writeValue(route,response);
    }

}
