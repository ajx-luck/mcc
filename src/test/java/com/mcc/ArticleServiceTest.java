package com.mcc;

import com.mcc.domain.Article;
import com.mcc.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by B04e on 2018/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
    @Autowired
    ArticleService mArticleService;

    @Test
    public void testCreateArticle(){
        for(int i=0;i<21;i++) {
            Article article = new Article();
            article.setTitle("标题"+i);
            article.setContent("内容"+i);
            article.setEnglishTitle("Title"+i);
            article.setEnglishContent("Content"+i);
            article.setShowDate("2017-09-21");
            article.setCreateTime(new Date().getTime());
            mArticleService.createArticle(article);
        }
    }

    @Test
    public void testFindAll(){
        String str = mArticleService.findAll(0);
        System.out.println(str);
        assert (str != null);
    }
}
