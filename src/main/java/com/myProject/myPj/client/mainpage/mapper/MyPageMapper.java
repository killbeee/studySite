package com.myProject.myPj.client.mainpage.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.PostVo;

@Mapper
public interface MyPageMapper {



	boolean insertPostInfo(Map<String, Object> paramMap);


	List<Map<String, Object>> getShareTodayPost(Map<String, Object> paramMap);


}