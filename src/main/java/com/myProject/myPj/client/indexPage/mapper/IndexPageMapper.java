package com.myProject.myPj.client.indexPage.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.PostVo;
import com.myProject.myPj.vo.ReplyVo;

@Mapper
public interface IndexPageMapper {

	PostVo getPostDetail(String keyId);

	ReplyVo getReplyDetail(String keyId);
}