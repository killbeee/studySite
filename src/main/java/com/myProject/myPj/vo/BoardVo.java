package com.myProject.myPj.vo;


import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class BoardVo {
  
	private String id;
    private String title;
	private String content;
    private String writer;
    private String viewCnt;
    private Integer noticeYn;
    private Integer deleteYn;
    private Date createdDate;
    private Date modifiedDate;
	

}