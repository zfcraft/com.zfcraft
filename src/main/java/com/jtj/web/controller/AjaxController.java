package com.jtj.web.controller;

import com.jtj.web.dao.AjaxDao;
import com.jtj.web.entity.Ajax;
import com.jtj.web.service.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class AjaxController {
    @Autowired
    AjaxService ajaxService;
    AjaxDao ajaxDao;

    @RequestMapping("/ajax")
//    @ResponseBody
    public Object ajaxList(@RequestBody Ajax ajax ) {
      //  ajax.setName(name);
        System.out.println("name = " +ajax.getName());
        System.out.println("name = " +ajax.getName());

 return ajax;
    }

}
