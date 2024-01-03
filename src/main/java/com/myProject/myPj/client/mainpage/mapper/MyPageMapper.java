package com.myProject.myPj.client.mainpage.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.PostVo;

@Mapper
public interface MyPageMapper {



	boolean insertPostInfo(Map<String, Object> paramMap);

	boolean updatePostInfo(Map<String, Object> paramMap);
	
	boolean insertReply(Map<String, Object> paramMap);
	
	boolean delReply(Map<String, Object> paramMap);

	List<Map<String, Object>> getShareTodayPost(Map<String, Object> paramMap);
	
	Map<String, Object> getPost(Map<String, Object> paramMap);

	void deletePost(Map<String, Object> paramMap);


}