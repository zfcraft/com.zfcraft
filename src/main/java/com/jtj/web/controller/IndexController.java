package com.jtj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chen
 * 2020/3/13.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/mm")
    public String indexCar(){
        return "fanIndex";
    }
    @RequestMapping("/edit")
    public String edit(){
        return "edit";
    }
    @RequestMapping("/ajaxServlet1111")
    public String ajaxServlet1111(@RequestParam String name){
        System.out.println("name = " + name);
        return "edit";
    }

}
