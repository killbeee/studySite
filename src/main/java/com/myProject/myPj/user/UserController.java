package com.myProject.myPj.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myProject.myPj.vo.UserVo;

@Controller
public class UserController {
    @Autowired
	UserService userService;
    
    @SuppressWarnings("unused")
    @GetMapping("/kakao/callback")
    public String  kakaoCallback(ModelMap model,@RequestParam String code,HttpServletRequest request) {
    		ModelAndView mv = new ModelAndView();
            String access_Token = userService.getKaKaoAccessToken(code);
            UserVo userVo = userService.createKakaoUser(access_Token);
            
            long id = (long) userVo.getSocialUserId();
            UserVo checkUser = userService.getUser(id);
            try {
            	if(checkUser == null) {
//                	model.addAttribute("socialUserId", userVo.getSocialUserId());
//                	model.addAttribute("userNick", userVo.getUserNick());
//                	model.addAttribute("applyType", "kakao");
            		
                	
                	return "/common/register.html";
                }else {
                	HttpSession session = (HttpSession)request.getSession();
                	session.setAttribute("UserVo", checkUser);

                	return "/index.html";
                }
				
				
			} catch (Exception e) {
				e.printStackTrace();
            	return "/index.html";
			}
            
            
            
            
    }
    
//    @SuppressWarnings("unused")
//    @GetMapping("/naver/callback")
//    public String  naverCallback(ModelMap model,@RequestParam String code,HttpServletRequest request) {
//    		ModelAndView mv = new ModelAndView();
//            String access_Token = userService.getNaverAccessToken(code);
//            UserVo userVo = userService.getNaverUserInfo(access_Token,code);
//            
//            String id = String.valueOf(userVo.getSocialUserId());
//            UserVo checkUser = userService.getUser(id);
//            try {
//            	if(checkUser == null) {
//                	model.addAttribute("socialUserId",id);
//                	model.addAttribute("userName", userVo.getUserName());
//                	model.addAttribute("userEmail", userVo.getUserEmail());
//                	model.addAttribute("applyType", "naver");
//                	
//                	return "/common/register.html";
//                }else {
//                	HttpSession session = (HttpSession)request.getSession();	
//                	session.setAttribute("UserVo", checkUser);
//
//                	return "/client/index.html";
//                }
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//            	return "/client/index.html";
//			}
//            
//            
//            
//            
//    }
    @RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		//세션을 삭제
		HttpSession session = request.getSession(false); 
       
		if(session != null) {
			session.invalidate();
		}
		return "/index.html";
	}
//    @RequestMapping("/registeration")
//	public String goRegisteration(UserVo userVo) {
//		userService.regRegisteration(userVo);
//    	
//    	
//		return "client/index.html";
//	}
}

