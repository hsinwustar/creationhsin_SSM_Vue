package com.whx.creationhsin.dao;

import com.whx.creationhsin.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */
    @Select("select * from user where useremail = #{useremail} and userpwd = #{userpwd}")
    public User findUserByNamePwd(User user) throws Exception;


    /**
     * 查询用户所有信息
     * @return
     * @throws Exception
     */
    @Select("select *from user")
    List<User> findAll() throws Exception;

    @Select("select *from user")
    List<User> findByPage() throws Exception;

    /**
     * 从信息库删除用户信息，数据库保留数据
     * @param uid
     * @throws Exception
     */
    @Delete("update user set userstatus = '封禁中' where uid = #{uid}")
    void deleteUserById(String uid) throws Exception;


    /**
     * 修改用户信息
     * @param user
     * @throws Exception
     */
    @Update("update user set  usertype = #{usertype} ,  userstatus = #{userstatus} where uid = #{uid}")
    void updateUserMessage(User user) throws Exception;


    /**
     * 根据用户id进行寻找用户
     * @param uid
     * @return
     * @throws Exception
     */
    @Select("select * from user where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "userstatus", property = "userstatus"),
            @Result(column = "useremail",property = "useremail"),
            @Result(column = "usertype", property = "usertype"),
            @Result(column = "useraddress", property = "useraddress"),
            @Result(column = "usericon", property = "usericon"),
            @Result(column = "userdescribe", property = "userdescribe"),

            })
    public User findUserById(String uid) throws Exception;

    /**
     * 编辑个人介绍
     */
    @Update("update user set userdescribe = #{userdescribe} where uid = #{uid}")
    void updateUserDescribe(User user) throws Exception;

    /**
     * 用户自己编辑资料
     */
    @Update("update user set username = #{username},useremail = #{useremail},useraddress = #{useraddress},usericon = #{usericon},userdescribe = #{userdescribe} where uid = #{uid}")
    void updateUserModify(User user) throws Exception;


    /**
     * 根据用户id进行寻找用户(关注者查找被关注者)
     * @param
     * @return
     * @throws Exception
     */
    @Select("select * from user where uid=#{otherUserId}")
    @Results({
            @Result(id = true, property = "otherUserId", column = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "userstatus", property = "userstatus"),
            @Result(column = "useremail",property = "useremail"),
            @Result(column = "usertype", property = "usertype"),
            @Result(column = "useraddress", property = "useraddress"),
            @Result(column = "usericon", property = "usericon"),
            @Result(column = "userdescribe", property = "userdescribe"),

    })
    public User findUserByOtherUId(String otherUserId) throws Exception;
}
