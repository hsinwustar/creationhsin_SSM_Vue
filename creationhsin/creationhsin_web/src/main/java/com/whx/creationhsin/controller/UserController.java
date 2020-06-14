package com.whx.creationhsin.controller;

import com.whx.creationhsin.domain.PageBean;
import com.whx.creationhsin.domain.User;
import com.whx.creationhsin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        User user2 = userService.findUserByNamePwd(user);
        System.out.println("userController:::::" + user2);
        if (user2 != null) {
            String userstatus = user2.getUserstatus();
            System.out.println("userstatus::::"+userstatus);
            if (userstatus.equals("正常使用")) {
                map.put("user", user2);
            } else {
                map.put("msg1", "该用户正在封禁中，请等待解封！");
            }
        }else{
            map.put("msg2", "用户名或密码错误！");
        }
        System.out.println("map::::"+map);
        return map;
    }


    @RequestMapping("/findAll")
    public List<User> findAll()throws Exception{
        return userService.findAll();
    }


    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(@RequestParam("pageCode") int pageCode, @RequestParam("pageSize") int pageSize) throws Exception{
        return userService.findByPage(pageCode, pageSize);
    }


    @RequestMapping("/updateUserMessage")
    public String updateUserMessage(@RequestBody User user, RedirectAttributes attr) throws Exception{
        userService.updateUserMessage(user);
        attr.addAttribute("uid", user.getUid());
        return "success";
    }


    @RequestMapping("/deleteUserById")
    public void deleteUserById(@RequestParam(name = "uid") String uid) throws Exception{
        userService.deleteUserById(uid);
    }

    @RequestMapping("/findUserById")
    public User findUserById(@RequestParam(name = "uid") String uid)throws Exception{
        return userService.findUserById(uid);
    }


    @RequestMapping("/updateUserDescribe")
    public String updateUserDescribe(@RequestBody User user, RedirectAttributes attr) throws Exception{
        userService.updateUserDescribe(user);
        attr.addAttribute("uid", user.getUid());
        return "success";
    }

    @RequestMapping("/updateUserModify")
    public String updateUserModify(@RequestBody User user, RedirectAttributes attr) throws Exception{
        userService.updateUserModify(user);
        attr.addAttribute("uid", user.getUid());
        return "success";
    }
}
