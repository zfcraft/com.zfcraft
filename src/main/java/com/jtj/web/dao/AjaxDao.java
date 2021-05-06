package com.jtj.web.dao;


import com.jtj.web.entity.Ajax;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@org.apache.ibatis.annotations.Mapper
@Component
@Repository
public interface AjaxDao extends Mapper<Ajax> {

}
