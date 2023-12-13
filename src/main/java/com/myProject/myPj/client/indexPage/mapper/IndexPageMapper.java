package com.myProject.myPj.client.indexPage.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.BoardVo;

@Mapper
public interface IndexPageMapper {
	 List<BoardVo> getUserList();
}