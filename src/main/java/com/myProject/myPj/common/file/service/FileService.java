package com.myProject.myPj.common.file.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myProject.myPj.common.file.mapper.FileMapper;
import com.myProject.myPj.common.mapper.CommonMapper;
import com.myProject.myPj.vo.FileVo;

@Service
public class FileService {
	 @Autowired
	 private FileMapper fileMapper;
	 @Autowired
	 private CommonMapper commonMapper;
	 @Value("${custom.imgPath}")
	 private String fileUploadPath;
	 public Map<String,Object> insertImgFile(MultipartHttpServletRequest req,String postType,String referKey) {
		 Map<String,Object> resultMap = new HashMap<>();
		 //SQL문 돌릴때 전해줄 파라미터 세팅
		 Map<String,Object> paramMap = new HashMap<>();
		 Map<String, String> keyMap = new HashMap<>();
		 paramMap.put("postType", postType);
		 paramMap.put("referKey", referKey);
		//get image file.
		 boolean sucOrfal = false;
	     List<MultipartFile> multipartFileList = new ArrayList<>();
	     try{
	    	 	MultipartFile files = req.getFile("img_upload");
		        
	            
            	 multipartFileList.add(files);

		         

		         if(multipartFileList.size()>0) {
		             for(MultipartFile file: multipartFileList) {
		            	
		            	 keyMap.put("alias", "PUI");
		            	 keyMap.put("tableName", "PJ_USER_INFO");
		         		 String fileKeyId = commonMapper.getNextPK(keyMap);
		            	 
		            	 paramMap.put("fileKeyId", fileKeyId);
		            	 String transFileName = changeFileName(file.getOriginalFilename());
		            	 
		            	 paramMap.put("transFileName", transFileName);
		            	 paramMap.put("oriFileName", file.getOriginalFilename());
		            	 paramMap.put("transFilePath", File.separator+postType+File.separator + transFileName);
		                 file.transferTo(new File(File.separator+postType+File.separator+ transFileName));
		                 sucOrfal = fileMapper.insertFileInfo(paramMap);
		             }
		         }
	         
	         }catch (Exception e){
	         e.printStackTrace();
	     }

	     //사이즈 확인
	     resultMap.put("sucoOrFal", sucOrfal);
	     resultMap.put("size", multipartFileList.size());
	     
	     return resultMap;
	 }
	//uuid를 통해서 파일 명을 유기적으로 변경함
	 private String changeFileName(String orginalName) throws Exception {
		 UUID uuid = UUID.randomUUID();
		 String changedName = uuid.toString()+"_"+orginalName;
		 
		 return changedName;
	 }
	 
	  public String store(MultipartFile file,String rootLocation) throws Exception {
	        try {
	            if (file.isEmpty()) {
	                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
	            }
	            String saveFileName = changeFileName(file.getOriginalFilename());
	            file.transferTo(new File(rootLocation+ File.separator + saveFileName));    
	            FileVo saveFile = new FileVo();
	            Map<String, String> keyMap = new HashMap<>();
	            keyMap.put("alias", "PUI");
	            keyMap.put("tableName", "PJ_USER_INFO");
        		String fileKeyId = commonMapper.getNextPK(keyMap);
           	 
        		saveFile.setFileId(fileKeyId);
	            saveFile.setTransFileName(saveFileName);
	            saveFile.setOriFileName(file.getOriginalFilename());
	            saveFile.setTransFilePath(rootLocation + File.separator + saveFileName);
	            saveFile.setRegDate(new Date().toString());
	            saveFile.setPostType("summerNoteImg");
	            fileMapper.summerNoteSave(saveFile);
	            return saveFileName;
	        } catch (IOException e) {
	            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
	        }
	   }

	  public FileVo selectSummerNoteSave(String fileId) throws Exception {
	       return fileMapper.selectSummerNoteSave(fileId);
	   }
	 
}