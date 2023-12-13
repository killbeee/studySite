package com.myProject.myPj.client.mainpage.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myProject.myPj.client.mainpage.service.MyPageService;
import com.myProject.myPj.common.file.service.FileService;
import com.myProject.myPj.common.mapper.CommonMapper;
import com.myProject.myPj.common.service.CommonService;
import com.myProject.myPj.vo.PostVo;
import com.myProject.myPj.vo.UserVo;

@Controller
public class MypageController {

    @Autowired
    private MyPageService myPageService;
    @Autowired
    private FileService fileService;
    @Autowired
    private CommonService commonService;
    
    @RequestMapping(value={"/client/portfolio/getPost.do"})
    public String getUserList(Model model,HttpServletRequest request) {
    	 
    	 List<PostVo> postList =  myPageService.getShareTodayPost();
    	 //파일을 나눠서 배열에 담은다음 다시 세팅
//    	 for(int i =0;i<postList.size();i++) {
//    		 postList.get(i).setFileArr(postList.get(i).getFileList());
//    	 }
    	 
         model.addAttribute("postList", postList);
    	
        return "jsonView";
     
    }
    @Transactional
    @RequestMapping(value = "/client/portfolio/createToday.do",method = RequestMethod.POST)
    public String createToday(Model model,MultipartHttpServletRequest req,HttpServletRequest request){

    	// Access regular form fields
    	Map<String,Object> paramMap = new HashMap<>();
    	Map<String,Object> resultMap = new HashMap<>();
    	Map<String,String> keyMap = new HashMap<>();
    	boolean suc = false;
    	
        String postType = req.getParameter("postType");
        String postTitle = req.getParameter("postTitle");
        String postContent = req.getParameter("postContent");
        
        keyMap.put("alias", "PT");
        keyMap.put("tableName", "POST_TABLE");
        
        String referKey = commonService.getPkId(keyMap);
        
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
        
        return "index.html";
    }
}