///*
// *  Copyright 2019-2020 Zheng Jie
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *  http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// */
//package com.jtj.web.controller.log;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import me.zhengjie.annotation.Log;
//import me.zhengjie.service.LogService;
//import me.zhengjie.service.dto.LogQueryCriteria;
//import me.zhengjie.utils.SecurityUtils;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author Zheng Jie
// * @date 2018-11-24
// */
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/logs")
//
//public class LogController {
//
//    private final LogService logService;
//
//    @Log("导出数据")
//    @ApiOperation("导出数据")
//    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check()")
//    public void download(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
//        criteria.setLogType("INFO");
//        logService.download(logService.queryAll(criteria), response);
//    }
//
//    @Log("导出错误数据")
//    @ApiOperation("导出错误数据")
//    @GetMapping(value = "/error/download")
//    @PreAuthorize("@el.check()")
//    public void downloadErrorLog(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
//        criteria.setLogType("ERROR");
//        logService.download(logService.queryAll(criteria), response);
//    }
//    @GetMapping
//    @ApiOperation("日志查询")
//    @PreAuthorize("@el.check()")
//    public ResponseEntity<Object> query(LogQueryCriteria criteria, Pageable pageable){
//        criteria.setLogType("INFO");
//        return new ResponseEntity<>(logService.queryAll(criteria,pageable), HttpStatus.OK);
//    }
//
//
//
//
//
//}
