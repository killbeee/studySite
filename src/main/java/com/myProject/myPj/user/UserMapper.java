package com.myProject.myPj.user;


import org.apache.ibatis.annotations.Mapper;

import com.myProject.myPj.vo.UserVo;

@Mapper
public interface UserMapper {
	int checkUser(long id);
	UserVo getUser(Object id);
	void regRegisteration(UserVo userVo);
	void updateLastLogin(long id);
}