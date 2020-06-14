package com.whx.creationhsin.controller;

import com.whx.creationhsin.domain.Follow;
import com.whx.creationhsin.domain.FollowArticle;
import com.whx.creationhsin.domain.FollowThematic;
import com.whx.creationhsin.service.FollowService;
import com.whx.creationhsin.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/follow")
@ResponseBody
@CrossOrigin(origins="*",maxAge=3600)
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    UUIDUtils uuidUtils;

    @RequestMapping("/addFollowUser")
    public void addFollowUser(@RequestBody Follow follow) throws Exception{
        followService.addFollowUser(follow);
        System.out.println("article:::::::::" + follow);
    }

    @ResponseBody
    @RequestMapping("/findFollowUserStatus")
    public Follow findFollowUserStatus(@RequestParam(name = "uid") String uid,@RequestParam(name = "otherUserId") String otherUserId)throws Exception{
        return followService.findFollowUserStatus(uid,otherUserId);
    }

    @ResponseBody
    @RequestMapping("/updateFollowStatus")
    public void updateFollowStatus(@RequestParam(name = "uid") String uid,@RequestParam(name = "otherUserId") String otherUserId) throws Exception{
        followService.updateFollowStatus(uid,otherUserId);
    }


    @RequestMapping("/addFollowArticle")
    public void addFollowArticle(@RequestBody FollowArticle followArticle) throws Exception{
        followArticle.setFollowarticleId(uuidUtils.getUUID());
        followService.addFollowArticle(followArticle);
        System.out.println("article:::::::::" + followArticle);
    }

    @RequestMapping("/findFollowArticle")
    public List<FollowArticle> findFollowArticle(@RequestParam(name = "uid") String uid)throws Exception{
        return followService.findFollowArticle(uid);
    }

    @ResponseBody
    @RequestMapping("/deleteFollowArticleStatus")
    public void deleteFollowArticleStatus(@RequestParam(name = "uid") String uid,@RequestParam(name = "articleId") String articleId) throws Exception{
        followService.deleteFollowArticleStatus(uid,articleId);
    }

    @ResponseBody
    @RequestMapping("/findFollowArticleById")
    public FollowArticle findFollowArticleById(@RequestParam(name = "uid") String uid,@RequestParam(name = "articleId") String articleId)throws Exception{
        return followService.findFollowArticleById(uid,articleId);
    }

    @RequestMapping("/addFollowThematic")
    public void addFollowThematic(@RequestBody FollowThematic followThematic) throws Exception{
        followService.addFollowThematic(followThematic);
        System.out.println("article:::::::::" + followThematic);
    }

    @ResponseBody
    @RequestMapping("/updateFollow")
    public void updateFollow(@RequestParam(name = "uid") String uid,@RequestParam(name = "thematicId") String thematicId) throws Exception{
        followService.updateFollow(uid,thematicId);
    }

    @ResponseBody
    @RequestMapping("/findFollowThematicStatus")
    public FollowThematic findFollowThematicStatus(@RequestParam(name = "uid") String uid,@RequestParam(name = "thematicId") String thematicId)throws Exception{
        return followService.findFollowThematicStatus(uid,thematicId);
    }

    @ResponseBody
    @RequestMapping("/findFollowThematicOfUser")
    public List<FollowThematic> findFollowThematicOfUser(@RequestParam(name = "thematicId") String thematicId)throws Exception{
        return followService.findFollowThematicOfUser(thematicId);
    }

    @ResponseBody
    @RequestMapping("/findFollowUserOfOther")
    public List<Follow> findFollowUserOfOther(@RequestParam(name = "otherUserId") String otherUserId)throws Exception{
        return followService.findFollowUserOfOther(otherUserId);
    }

    @ResponseBody
    @RequestMapping("/findFollowUserListOfPersonalFollow")
    public List<Follow> findFollowUserListOfPersonalFollow(@RequestParam(name = "uid") String uid)throws Exception{
        return followService.findFollowUserListOfPersonalFollow(uid);
    }

    @ResponseBody
    @RequestMapping("/findFollowThematicListOfPersonalFollow")
    public List<FollowThematic> findFollowThematicListOfPersonalFollow(@RequestParam(name = "uid") String uid)throws Exception{
        return followService.findFollowThematicListOfPersonalFollow(uid);
    }
}
