package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RestfulController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/select")
    public int postApi(){
        return userMapper.selectId();
    }
}
