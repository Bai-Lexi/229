package edu.hit.chaijiarui.web.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hit.chaijiarui.domain.ResultInfo;
import edu.hit.chaijiarui.domain.User;
import edu.hit.chaijiarui.service.UserService;
import edu.hit.chaijiarui.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    //声明userservice业务对象
    private UserService service = new UserServiceImpl();

    //注册功能
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册

        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //响应结果
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
        }
        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }


    //登录功能
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service查询

        User u = service.login(user);

        ResultInfo info = new ResultInfo();
        //判断用户对象是否为null
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //判断登录成功
        if (u != null) {
            //登录成功
            info.setFlag(true);
            HttpSession sessions = request.getSession();
            sessions.setAttribute("user", u);
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    //查询单个对象
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");

        /*ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);*/
        writeValue(user,response);
    }

    //退出功能
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();

        //跳转页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}