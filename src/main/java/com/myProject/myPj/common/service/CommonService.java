package com.myProject.myPj.common.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.myPj.common.mapper.CommonMapper;
import com.myProject.myPj.vo.PostVo;

@Service
public class CommonService {

    @Autowired
    private CommonMapper commonMapper;

    public String getPkId(Map<String,String> map) {
    	
        return commonMapper.getNextPK(map);
    }


}