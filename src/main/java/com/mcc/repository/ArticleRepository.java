package com.mcc.repository;

import com.mcc.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by B04e on 2018/3/20.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> ,PagingAndSortingRepository<Article, Long> {
    Article findById(@Param("id") Long id);
}
