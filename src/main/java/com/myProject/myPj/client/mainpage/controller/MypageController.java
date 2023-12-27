package com.myProject.myPj.client.mainpage.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myProject.myPj.client.mainpage.service.MyPageService;
import com.myProject.myPj.common.file.service.FileService;
import com.myProject.myPj.common.service.CommonService;
import com.myProject.myPj.vo.PostVo;

@Controller
public class MypageController {

    @Autowired
    private MyPageService myPageService;
    @Autowired
    private FileService fileService;
    @Autowired
    private CommonService commonService;
    
    @RequestMapping(value={"/client/portfolio/getPost.do"})
    public String getUserList(HttpServletRequest req,@RequestParam Map<String, Object> paramMap,Model model) {
   
    	 List<Map<String,Object>> postList =  myPageService.getShareTodayPost(req,paramMap);

         model.addAttribute("postList", postList);
    	
        return "jsonView";
     
    }
    @RequestMapping(value={"/client/portfolio/modifyPost.do"})
    public String modifyPost(@RequestParam Map<String, Object> paramMap,Model model) {
    	
    	 Map<String,Object> post =  myPageService.modifyPost(paramMap);

         model.addAttribute("post", post);
         model.addAttribute("type", paramMap.get("type"));
    	
        return "/postModify.html";
     
    }
    @Transactional
    @RequestMapping(value = "/client/portfolio/createToday.do",method = RequestMethod.POST)
    public String createToday(Model model,MultipartHttpServletRequest req,HttpServletRequest request){
    	
    	// Access regular form fields
    	Map<String,Object> paramMap = new HashMap<>();
    	Map<String,Object> resultMap = new HashMap<>();
    	Map<String,String> keyMap = new HashMap<>();
    	boolean suc = false;
    	String type = req.getParameter("type");
        String postType = req.getParameter("postType");
        String postTitle = req.getParameter("postTitle");
        String postContent = req.getParameter("postContent");
        
        keyMap.put("alias", "PT");
        keyMap.put("tableName", "POST_TABLE");
        String referKey = "";
        if(type.equals("modify")) {
        	referKey = (String) req.getParameter("id");
		}else {
			referKey =  commonService.getPkId(keyMap);
		}

        paramMap.put("type", type);
        paramMap.put("postType", postType);
        paramMap.put("postTitle", postTitle);
        paramMap.put("postContent", postContent);
        paramMap.put("id", referKey);
//        HttpSession session = request.getSession(false);
//    	UserVo userVo;
//    	String userId = "";
//    		userVo = (UserVo)session.getAttribute("UserVo");
//    		userId = userVo.getUserId();
    	paramMap.put("writer", "KillBee");
        //게시물 정보 삽입
        suc = myPageService.insertPostInfo(paramMap);
        
        if(suc) {
        	if(req.getFile("img_upload") != null) {
        		MultipartFile imageFile = req.getFile("img_upload");
            	if(imageFile != null && !imageFile.isEmpty()) {
            		// 파일 정보삽입
            	 resultMap =  fileService.insertImgFile(req,postType,referKey);
            	}
        	}
        }
        // Access uploaded files

        
        model.addAttribute("resultMap", resultMap);
        
        return "jsonView";
    }
    
    @RequestMapping(value={"/client/portfolio/deletePost.do"})
    public String deletePost(@RequestParam Map<String, Object> paramMap,Model model) {
    	
    	myPageService.deletePost(paramMap);
    	
        return "/index.html";
    }
    @Transactional
    @RequestMapping(value = "/client/portfolio/addReply.do",method = RequestMethod.POST)
    public String addReply(Model model,@RequestParam Map<String, Object> paramMap){
    	Boolean checkSuc = false;
    	checkSuc = myPageService.insertReply(paramMap);
        model.addAttribute("checkSuc", checkSuc);
        return "jsonView";
    }
    
}