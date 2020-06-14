package com.whx.creationhsin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whx.creationhsin.dao.UserDao;
import com.whx.creationhsin.domain.PageBean;
import com.whx.creationhsin.domain.User;
import com.whx.creationhsin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User findUserByNamePwd(User user) throws Exception{
        System.out.println("userServiceImpl::::"+user);
        return userDao.findUserByNamePwd(user);
    }

    @Override
    public List<User> findAll()throws Exception {
        System.out.println("user:::::findALL:::::::"+userDao.findAll());
        return userDao.findAll();
    }

    @Override
    public PageBean findByPage(int pageCode, int pageSize) throws Exception{
        PageHelper.startPage(pageCode,pageSize);
        Page<User> page = (Page<User>) userDao.findByPage();
        System.out.println("user:::::findbypage:::::::"+page);
        return new PageBean(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteUserById(String uid) throws Exception{
        userDao.deleteUserById(uid);
    }

    @Override
    public void updateUserMessage(User user) throws Exception{
        userDao.updateUserMessage(user);

    }

    @Override
    public User findUserById(String uid) throws Exception{
        return userDao.findUserById(uid);
    }

    @Override
    public void updateUserDescribe(User user)throws Exception {
        userDao.updateUserDescribe(user);
    }

    @Override
    public void updateUserModify(User user)throws Exception {
        userDao.updateUserModify(user);
    }
}
