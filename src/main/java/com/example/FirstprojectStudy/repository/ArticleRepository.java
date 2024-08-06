package com.example.FirstprojectStudy.repository;


import com.example.FirstprojectStudy.controller.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
