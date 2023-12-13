package com.myProject.myPj.common.file.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.FileVo;

@Mapper
public interface FileMapper  {
	 
	boolean insertFileInfo(Map<String, Object> paramMap);
	
	 void summerNoteSave(FileVo fileVo);
	 
	FileVo selectSummerNoteSave(String fileId);
}