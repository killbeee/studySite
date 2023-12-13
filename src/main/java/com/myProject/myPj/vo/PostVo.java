package com.myProject.myPj.vo;


import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PostVo {
  
	private String id;
    private String title;
	private String content;
    private String writer;
    private String viewCnt;
    private Integer noticeYn;
    private String deleteYn;
    private Date createDate;
    private Date modifiedDate;
    private String postType;
	private String fileList;
	private String[] fileArr;

    public void setFileArr(String fileList) {
        if (fileList != null && !fileList.isEmpty()) {
        	fileList = fileList.replaceAll("\\\\", "");
            String[] fileIdStrings = fileList.split(",");
            for(int i = 0 ;i<fileIdStrings.length;i++) {
            	fileIdStrings[i]=fileIdStrings[i].trim();
            }
            this.fileArr = fileIdStrings;
        } else {
            this.fileArr = new String[0];
        }
    }
}