package com.whx.creationhsin.service;

import com.whx.creationhsin.domain.PageBean;
import com.whx.creationhsin.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */

   public User findUserByNamePwd(User user) throws Exception;

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
   public List<User> findAll()throws Exception;

    /**
     * 分页查询用户信息
     * @param pageCode
     * @param pageSize
     * @return
     * @throws Exception
     */
   public PageBean findByPage(int pageCode,int pageSize) throws Exception;

    /**
     * 从信息库删除用户信息，数据库保留数据
     * @param uid
     * @throws Exception
     */
    void deleteUserById(String uid) throws Exception;

    /**
     * 更改用户信息
     * @param user
     * @throws Exception
     */
    void updateUserMessage(User user)throws Exception;


    /**
     * 根据id寻找用户
     * @param uid
     * @return
     * @throws Exception
     */
    public User findUserById(String uid) throws Exception;

    /**
     * 编辑个人介绍
     */

    void updateUserDescribe(User user)throws Exception;

    /**
     * 用户编辑个人资料
     */
    void updateUserModify(User user)throws Exception;

}
