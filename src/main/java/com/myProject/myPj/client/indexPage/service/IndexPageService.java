package com.myProject.myPj.client.indexPage.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.myPj.client.indexPage.mapper.IndexPageMapper;
import com.myProject.myPj.vo.PostVo;
import com.myProject.myPj.vo.ReplyVo;

@Service
public class IndexPageService {

    @Autowired
    private IndexPageMapper indexPageMapper;

    public PostVo getPostDetail(String keyId) {
    	
    	PostVo postDetail = indexPageMapper.getPostDetail(keyId);
    	
    	return postDetail;
    }
    
    public List<ReplyVo> getReplyDetail(String keyId) {
    	List<ReplyVo> replyDetail = null;
    	replyDetail = indexPageMapper.getReplyDetail(keyId);
    	if(replyDetail != null) {
    		for(ReplyVo vo : replyDetail) {
    			vo.getBetweenTime();
    		}
    		
    	}
    	
    	return replyDetail;
    }

	public List<PostVo> getPostList(Map<String, Object> param) {
		List<PostVo> list= indexPageMapper.getPostList(param);
		
		return list;
	}
}