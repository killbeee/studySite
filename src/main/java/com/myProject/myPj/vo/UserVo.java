package com.myProject.myPj.vo;


import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class UserVo {
  
	private String userId;
	private String userName;
	private String userNick;
	private String userEmail;
	private String userAddress;
	private String applyType;
	private Object socialUserId;
	private String handNo;
	private Date regDate;
	private Date lastConnect;

}