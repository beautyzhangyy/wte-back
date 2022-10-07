package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class hello {
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world999999";
    }
}