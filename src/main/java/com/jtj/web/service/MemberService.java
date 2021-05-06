package com.jtj.web.service;

import com.jtj.web.dao.MemberDao;
import com.jtj.web.pojo.Member;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberService {
@Autowired(required = false)
//@Resource
MemberDao memberDao;
    public Object findByTelephone(String telephone) {


        return memberDao.selectByPrimaryKey(memberDao);
//        return memberDao.findByTelephone(telephone);
    }
}
