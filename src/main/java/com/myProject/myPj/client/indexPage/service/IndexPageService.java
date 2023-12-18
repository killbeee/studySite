package com.myProject.myPj.client.indexPage.service;


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
    
    public ReplyVo getReplyDetail(String keyId) {
    	
    	ReplyVo replyDetail = indexPageMapper.getReplyDetail(keyId);
    	replyDetail.getBetweenTime();
    	return replyDetail;
    }
}