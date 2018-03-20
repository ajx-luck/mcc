package com.mcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.mcc.domain.Article;
import com.mcc.repository.ArticleRepository;
import com.mcc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by B04e on 2018/3/20.
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository mArticleRepository;
    @Override
    public String findAll(int page) {
        Pageable pageable = new PageRequest(page,10, Sort.Direction.ASC, "id");
        Page<Article> articles = mArticleRepository.findAll(pageable);
        return JSON.toJSONString(articles);
    }

    @Override
    public void createArticle(Article article) {
        mArticleRepository.save(article);
    }


}
