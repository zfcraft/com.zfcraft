package com.jtj.web.dao;

import com.jtj.web.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleDao {
    /**
     * 添加文章
     * @param article
     * @return
     */
    @Insert("insert into article values(#{articleId},#{author},#{createTime},#{title},#{summary},#{content},#{type},#{category},#{tags},#{read},#{comment})")
    int addArticle(Article article);


    /**
     * 删除文章
     * @param id
     * @return
     */
    @Delete("delete from article where article_id = #{id}")
    int deleteArticle(long id);

    /**
     * 更新文章
     * @param article
     * @return
     */
    @Update("update article set author=#{author},title=#{title},summary=#{summary},content=#{content},type=#{type},category=#{category},tags=#{tags} where article_id=#{articleId}")
    int updateArticle(Article article);
}
