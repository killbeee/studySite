package com.myProject.myPj.client.indexPage.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.myPj.client.indexPage.service.IndexPageService;
import com.myProject.myPj.vo.PostVo;
import com.myProject.myPj.vo.ReplyVo;

@Controller
public class IndexPageController {

    @Autowired
    private IndexPageService indexPageService;

    @RequestMapping(value={"/","/index.do"})
    public String getUserList(Model model) {
      
        return "index.html";
    }
    @RequestMapping("/post/postDetail.do")
    public String goMakePost(Model model) { 
        model.addAttribute("type", model.getAttribute("type"));
        return "postDetail.html";
    }
    @GetMapping("/login.do")
    public String goLoginPage(Model model) {
          
   
        return "common/login.html";
    }
    @RequestMapping("/post/showPost.do")
    public String showPost(@RequestParam String keyId, Model model) { 
    	PostVo postDetail = indexPageService.getPostDetail(keyId);
    	List<ReplyVo> replyDetail = indexPageService.getReplyDetail(keyId);
    	model.addAttribute("postDetail", postDetail);
    	model.addAttribute("replyDetail", replyDetail);
        return "showPost.html";
    }
    @RequestMapping("/post/getPostList.do")
    public String getPostList(@RequestParam Map<String,Object> param, Model model) { 
    	
    	List<PostVo> postList = indexPageService.getPostList(param);
    	
    	model.addAttribute("postList",postList);
        return "jsonView";
    }
    
}