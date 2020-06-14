package com.whx.creationhsin.controller;

import com.whx.creationhsin.domain.Thematic;
import com.whx.creationhsin.domain.ThematicArticle;
import com.whx.creationhsin.service.ThematicService;
import com.whx.creationhsin.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/thematic")
@ResponseBody
@CrossOrigin(origins="*",maxAge=3600)
public class ThematicController {

    @Autowired
    UUIDUtils uuidUtils;

    @Autowired
    ThematicService thematicService;

    @RequestMapping("/addThematic")
    public void AddThematic(@RequestBody Thematic thematic) throws Exception{
        thematic.setThematicId(uuidUtils.getUUID());
        System.out.println("id:::::"+uuidUtils.getUUID());
        thematicService.AddThematic(thematic);
        System.out.println("thematic:::::::::" + thematic);
    }


    @RequestMapping("/findThematicById")
    public Thematic findThematicById(@RequestParam(name = "thematicId") String thematicId)throws Exception{
        return thematicService.findThematicById(thematicId);
    }

    @RequestMapping("/findAll")
    public List<Thematic> findAll()throws Exception{
        return thematicService.findAll();
    }

    @RequestMapping("/addArticleToThematic")
    public void addArticleToThematic(@RequestBody ThematicArticle thematicArticle) throws Exception{
        thematicService.addArticleToThematic(thematicArticle);
    }

    @ResponseBody
    @RequestMapping("/findArticleToThematicStatus")
    public ThematicArticle findArticleToThematicStatus(@RequestParam(name = "articleId") String articleId,@RequestParam(name = "thematicId") String thematicId)throws Exception{
        return thematicService.findArticleToThematicStatus(articleId,thematicId);
    }

    @ResponseBody
    @RequestMapping("/updateArticleByThematic")
    public void updateArticleByThematic(@RequestParam(name = "articleId") String articleId,@RequestParam(name = "thematicId") String thematicId) throws Exception{
        thematicService.updateArticleByThematic(articleId,thematicId);
    }


    @RequestMapping("/findAllArticleInThematic")
    public List<ThematicArticle> findAllArticleInThematic(@RequestParam(name = "thematicId") String thematicId)throws Exception{
        return thematicService.findAllArticleInThematic(thematicId);
    }

    @RequestMapping("/findAllThematicInArticle")
    public List<ThematicArticle> findAllThematicInArticle(@RequestParam(name = "articleId") String articleId)throws Exception{
        return thematicService.findAllThematicInArticle(articleId);
    }

    @RequestMapping("/findAllThematicInUser")
    public List<Thematic> findAllThematicInUser(@RequestParam(name = "uid") String uid)throws Exception{
        return thematicService.findAllThematicInUser(uid);
    }

    @ResponseBody
    @RequestMapping("/deleteThematic")
    public void deleteThematic(@RequestParam(name = "thematicId") String thematicId) throws Exception{
        thematicService.deleteThematic(thematicId);
    }



    @RequestMapping("/updateThematicModify")
    public String updateThematicModify(@RequestBody Thematic thematic) throws Exception{
        thematicService.updateThematicModify(thematic);
        return "success";
    }
}
