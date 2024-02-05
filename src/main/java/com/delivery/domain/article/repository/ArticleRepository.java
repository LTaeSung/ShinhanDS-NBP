package com.delivery.domain.article.repository;

import com.delivery.domain.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

}
