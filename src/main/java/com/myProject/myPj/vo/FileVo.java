package com.myProject.myPj.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class FileVo {
  
	private String fileId;
    private String fileReferKey;
	private String oriFileName;
    private String transFileName;
    private String regDate;
    private String postType;
    private String transFilePath;
	
}