package com.jtj.web.dao;

import com.jtj.web.pojo.Article;
import com.jtj.web.pojo.Member;
import com.jtj.web.pojo.User;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface MemberDao extends Mapper<User> {

}
