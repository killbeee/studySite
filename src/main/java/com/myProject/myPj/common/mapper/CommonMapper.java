package com.myProject.myPj.common.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
	public String getNextPK(Map<String,String> paramMap);
}