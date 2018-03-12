package com.scrm.message.controller;

import com.scrm.message.entity.WtUserDetailInfo;
import com.scrm.message.mapper.WtUserDetailInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/25/025.
 */
@RestController
@EnableAutoConfiguration
public class HomeController {

    @Autowired
    WtUserDetailInfoDao wtUserDetailInfoDao;

    @RequestMapping("/hello")
    public String index(){
        WtUserDetailInfo w = new WtUserDetailInfo();
        w.setOpenId("121231231");
        wtUserDetailInfoDao.insert(w);
        return "hello world";
    }
}
