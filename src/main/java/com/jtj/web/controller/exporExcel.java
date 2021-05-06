package com.jtj.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jtj.web.dao.vo;

public class exporExcel {

    @RequestMapping("/expor")
    public String exporExcel(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
        response.setContentType("application/msexcel;charset=UTF-8");//设置类型
        response.setHeader("Pragma", "No-cache");//设置头
        response.setHeader("Cache-Control", "no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(ResourceUtils.getFile("classpath:excelTemplates/template.xls")).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        List<vo> list =new ArrayList<vo>();
        vo v = new vo();
        v.setColor("1");
        v.setDx("1");
        v.setNumber("1");
        v.setRk("1");
        v.setTerminal("1");
        v.setZq("1");
        v.setZt("1");
        v.setRk("1");
        list.add(v);
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        //填充两行list
        excelWriter.fill(list, fillConfig, writeSheet);
        excelWriter.fill(list, fillConfig, writeSheet);
        //填充普通变量
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bussinessRequest", "1");
        map.put("linkman", "1");
        map.put("linkphone", "1");
        map.put("orderdate", "1");
        map.put("leaddate", "1");
        map.put("entrct", "1");
        map.put("comstomer", "1");
        excelWriter.fill(map, writeSheet);
        excelWriter.finish();
        outputStream.flush();
        response.getOutputStream().close();
        return "system/test/tableTest";
    }
}
