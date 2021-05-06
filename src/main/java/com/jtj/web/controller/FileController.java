package com.jtj.web.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileController {
    @RequestMapping("/uploadFile")
    @ResponseBody
    public JSONObject httpUpload(@RequestParam(value = "files") MultipartFile files) throws IOException {
        JSONObject jsonObject =new JSONObject();
        String fileName = files.getOriginalFilename();
        File templateFileName = new File("D:\\workcore\\upload" + "\\"+fileName);
        files.transferTo(templateFileName);
        jsonObject.put("code", 200);
        jsonObject.put("meg", "上传成功！");
        return jsonObject;
    }
    @RequestMapping("/downloadFile")
    @ResponseBody
    public JSONObject httpDownload(HttpServletResponse httpServletResponse,@RequestParam("fileName") String fileName) throws IOException {
        JSONObject jsonObject =new JSONObject();
        String filePath = "D:\\workcore\\upload\\exception.xlsx";
        File file = new File(filePath);

        //浏览器下载中文名文件兼容性处理
//        String filaname = new String(templateFileName.getBytes("gb2312"),"ISO8859-1");
        // 清空response
        httpServletResponse.reset();
        //octet-stream 自动匹配文件类型
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=ISO8859-1");
        httpServletResponse.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
//        httpServletResponse.setHeader("Content-Length", String.valueOf(fileLength));

        InputStream is = new FileInputStream(file);
        OutputStream os = httpServletResponse.getOutputStream();



//        byte[] b = new byte[2048];
//        while (is.read(b) != -1) {
//            os.write(b);
//        }
//        is.close();

        jsonObject.put("code", 200);
        jsonObject.put("meg", "上传成功！");
        return jsonObject;
    }

}


