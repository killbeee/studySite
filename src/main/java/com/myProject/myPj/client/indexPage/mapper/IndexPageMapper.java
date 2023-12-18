package com.myProject.myPj.client.indexPage.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.PostVo;
import com.myProject.myPj.vo.ReplyVo;

@Mapper
public interface IndexPageMapper {

	PostVo getPostDetail(String keyId);

	List<ReplyVo> getReplyDetail(String keyId);
}