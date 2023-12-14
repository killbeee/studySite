package com.myProject.myPj.client.mainpage.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.myPj.client.mainpage.mapper.MyPageMapper;
import com.myProject.myPj.vo.PostVo;

@Service
public class MyPageService {

    @Autowired
    private MyPageMapper myPageMapper;

    public List<Map<String,Object>> getShareTodayPost(Map<String, Object> paramMap) {
    	paramMap.put("lastPost", Integer.parseInt((String)paramMap.get("curPage"))*6);
    	paramMap.put("firstPost", (Integer.parseInt((String)paramMap.get("curPage"))-1)*6);
    	
        return myPageMapper.getShareTodayPost(paramMap);
    }


	public boolean insertPostInfo(Map<String, Object> paramMap) {
		
		return myPageMapper.insertPostInfo(paramMap);
	}
    
}