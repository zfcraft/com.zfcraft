package com.jtj.web.service.impl;

import com.jtj.web.dao.AjaxDao;
import com.jtj.web.entity.Ajax;
import com.jtj.web.service.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjaxServiceImpl implements AjaxService {
    @Autowired AjaxDao ajaxDao;

    public Ajax ajaxService(Ajax ajax) {
    ajaxDao.insert(ajax);
        return ajax;
    }
}
