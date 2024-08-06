package com.example.FirstprojectStudy.controller;

import com.example.FirstprojectStudy.controller.entity.Article;
import com.example.FirstprojectStudy.dto.ArticleForm;
import com.example.FirstprojectStudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        //System.out.println(form.toString());

        // Article Entity 작성
        // Entity 생성
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        // 엔터티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "";
    }

}
