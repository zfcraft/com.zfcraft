package com.jtj.web.controller;

import com.jtj.web.dao.MemberDao;
import com.jtj.web.pojo.Member;

import com.jtj.web.pojo.User;
import com.jtj.web.service.MemberService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Resource
    MemberDao memberDao;

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        String username = user.getUsername();

        String password = user.getPassword();
        System.out.println(username);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username).andEqualTo("password", password);
        List<User> list = memberDao.selectByExample(example);
        for (User a : list) {
            System.out.println(a);
        }


        if (list.size() > 0) {

            jsonObject.put("code", 200);
            jsonObject.put("meg", "登录成功！");
//            return "登录成功";

        } else {
//            return "用户名或密码错误";
            jsonObject.put("code", 500);
            jsonObject.put("meg", "添加失败！");
        }

      return jsonObject;
    }

    @RequestMapping("/longin1")

    public User login1(@RequestBody User user) {
//        String telephone = (String) map.get("telephone");
//        String validateCode = (String) map.get("validateCode");

//
//        memberService.findByTelephone(telephone);
//        System.out.println("telephone = " + telephone);
        //user.setUsername("chen");
        List<User> list = memberDao.select(user);

        for (User a : list) {
            System.out.println(a);
        }
        // memberDao.selectAll();
        return user;
    }

    @RequestMapping("/register")
    @ResponseBody
    public JSONObject register(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        User user1 = memberDao.selectByPrimaryKey(user.getUsername());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        List<User> users = memberDao.selectByExample(example);
        //System.out.println("user1.getUsername() = " + user1.getUsername());
        if (users.size() > 0) {

            jsonObject.put("code", 500);
            jsonObject.put("meg", "该用户已存在！");
            return jsonObject;
        }
        int insert = memberDao.insert(user);
        if (insert > 0) {
            jsonObject.put("code", 200);
            jsonObject.put("meg", "注册成功！");
        } else {
            jsonObject.put("code", 500);
            jsonObject.put("meg", "注册失败！");
        }
        return jsonObject;
    }

    @RequestMapping("/upload")

    public String httpUpload(@RequestParam("files") MultipartFile files) {
//        String telephone = (String) map.get("telephone");
//        String validateCode = (String) map.get("validateCode");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("success","2");
        File dest = new File("D:\\log\\file");

//        files.transferTo(dest);

//        for(int i=0;i<files.length;i++){
//            String fileName = files[i].getOriginalFilename();  // 文件名
//            File dest = new File("D:\\log\\file");
//
//            files[i].transferTo(dest);


//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }
//            try {
//                files[i].transferTo(dest);
//            } catch (Exception e) {
////                log.error("{}",e);
////                object.put("success",2);
////                object.put("success",2);
////                object.put("result","程序错误，请重新上传");
////                return object.toString();
//            }
//        }
//        object.put("success",1);
//        object.put("result","文件上传成功");
        return "11";
    }
//
//        memberService.findByTelephone(telephone);
//        System.out.println("telephone = " + telephone);
//        return member.toString() + "\n" + user.toString();

}
