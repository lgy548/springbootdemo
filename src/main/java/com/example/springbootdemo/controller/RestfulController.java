package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Controller
public class RestfulController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/test")
    public String  index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/select")
    public Map<String,Object> select(){
        int i = userMapper.selectId();
        Map<String,Object> map = new HashMap<>();
        map.put("id",i);
        return map;
    }

}
