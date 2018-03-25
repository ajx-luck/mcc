package com.mcc.service;

import com.mcc.domain.Article;
import org.springframework.stereotype.Service;

/**
 * Created by B04e on 2018/3/20.
 */

public interface ArticleService {
    String findAll(int page);

    void createArticle(Article article);
}
