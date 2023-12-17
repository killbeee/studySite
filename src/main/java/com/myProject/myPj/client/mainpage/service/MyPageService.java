package com.myProject.myPj.client.mainpage.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.myPj.client.mainpage.mapper.MyPageMapper;
import com.myProject.myPj.vo.PagingVo;

@Service
public class MyPageService {

    @Autowired
    private MyPageMapper myPageMapper;

    public List<Map<String,Object>> getShareTodayPost(Map<String, Object> paramMap) {
    	PagingVo pagingVo = new PagingVo();
    	//현재 페이지  num
    	pagingVo.setCurrentPageNo(Integer.parseInt((String)paramMap.get("currentPageNo")));
    	//한페이지에 보여질 게시물 갯수
    	pagingVo.setRecordCountPerPage(6);
    	//페이지 리스트에 한번에 보일 페이지 갯수
    	pagingVo.setPageSize(5);
    	paramMap.put("firstIndex", pagingVo.getFirstRecordIndex());
    	paramMap.put("lastIndex", pagingVo.getLastRecordIndex());
    	
    	List<Map<String,Object>> paramList =  myPageMapper.getShareTodayPost(paramMap);
    	long totalCnt = (long)paramList.get(0).get("MAX_COUNT");
    	pagingVo.setTotalRecordCount((int)totalCnt);
    	Map<String,Object> pagingMap = new HashMap<>();
    	pagingMap.put("pagingVo", pagingVo);
    	

    	paramList.add(pagingMap);
        return paramList;
    }
    public Map<String,Object> modifyPost(Map<String, Object> paramMap) {

    	Map<String,Object> post =  myPageMapper.getPost(paramMap);    	

        return post;
    }
    public void deletePost(Map<String, Object> paramMap) {

    	myPageMapper.deletePost(paramMap);    	
    }

	public boolean insertPostInfo(Map<String, Object> paramMap) {
		boolean yes = true;
		if(paramMap.get("type").equals("modify")) {
			yes = myPageMapper.updatePostInfo(paramMap);
		}else {
			yes =  myPageMapper.insertPostInfo(paramMap);
		}
		
		
		
		return yes;
	}
    
}