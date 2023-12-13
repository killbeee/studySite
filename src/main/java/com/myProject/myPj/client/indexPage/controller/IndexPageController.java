package com.myProject.myPj.client.indexPage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.myPj.client.indexPage.service.IndexPageService;
import com.myProject.myPj.vo.BoardVo;

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
    
    @GetMapping("/client/myPage.do")
    public String go(Model model) { 
          
        return "client/myPage/myPageIndex.html";
    }
    @GetMapping("/login.do")
    public String goLoginPage(Model model) {
          
   
        return "common/login.html";
    }
    
    
}