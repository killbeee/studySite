package com.myProject.myPj.client.indexPage.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.PostVo;

@Mapper
public interface IndexPageMapper {

	PostVo getPostDetail(String keyId);
}