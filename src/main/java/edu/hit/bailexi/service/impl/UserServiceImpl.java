package edu.hit.chaijiarui.service.impl;

import edu.hit.chaijiarui.service.UserService;
import edu.hit.chaijiarui.domain.User;
import edu.hit.chaijiarui.dao.impl.UserDaoImpl;
import edu.hit.chaijiarui.dao.UserDao;

public class UserServiceImpl implements UserService {

    private UserDao dao= (UserDao) new UserDaoImpl();

    @Override  //注册
    public boolean regist(User user) {
        dao.save(user);
        return true;
    }

    @Override
    public User login(User InputedFromFrontSide) {  //登录
        User user = dao.findUserByUsernameAndPassword(InputedFromFrontSide.getUsername(), InputedFromFrontSide.getPassword());

        return user;
    }


}
