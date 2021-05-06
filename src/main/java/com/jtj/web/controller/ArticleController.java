package com.jtj.web.controller;

import com.jtj.web.dao.ArticleDao;
import com.jtj.web.pojo.Article;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    ArticleDao articleDao;

    @RequestMapping("/add")
    public JSONObject addArticle(Article article) {
        JSONObject jsonObject = new JSONObject();
        int flag = articleDao.addArticle(article);
        if (flag != 0) {
            jsonObject.put("code", 200);
            jsonObject.put("meg", "添加成功！");

        }
        jsonObject.put("code", 404);
        jsonObject.put("meg", "添加失败！");
        return jsonObject;
    }


    @RequestMapping("/update")
    public JSONObject updateArticle(Article article) {
        JSONObject jsonObject = new JSONObject();
        int flag = articleDao.updateArticle(article);
        if (flag != 0) {
            jsonObject.put("code", 200);
            jsonObject.put("meg", "更新成功！");

        }
        jsonObject.put("code", 404);
        jsonObject.put("meg", "更新失败！");
        return jsonObject;
    }

    @RequestMapping("/delete")
    public JSONObject deleteArticle(Article article) {
        JSONObject jsonObject = new JSONObject();
        int flag = articleDao.deleteArticle(article.getArticleId());
        if (flag != 0) {
            jsonObject.put("code", 200);
            jsonObject.put("meg", "删除成功！");

        }
        jsonObject.put("code", 404);
        jsonObject.put("meg", "删除失败！");
        return jsonObject;
    }

}
