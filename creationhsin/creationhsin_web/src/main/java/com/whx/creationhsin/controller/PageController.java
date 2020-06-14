package com.whx.creationhsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    /**
     * 跳转到错误页
     * @return
     */
    @RequestMapping("/error")
    public String showError(){
        return "error";
    }


    /**
     * 跳转到前台主页面
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }


    @RequestMapping("/changePwd")
    public String changePwd() {
        return "backuppwd";
    }
}
