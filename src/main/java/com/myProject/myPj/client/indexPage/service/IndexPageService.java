package com.myProject.myPj.client.indexPage.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.myPj.client.indexPage.mapper.IndexPageMapper;
import com.myProject.myPj.vo.BoardVo;

@Service
public class IndexPageService {

    @Autowired
    private IndexPageMapper indexPageMapper;


}