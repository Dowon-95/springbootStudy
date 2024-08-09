package com.example.FirstprojectStudy.service;

import com.example.FirstprojectStudy.dto.ArticleForm;
import com.example.FirstprojectStudy.entity.Article;
import com.example.FirstprojectStudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        // request 에서 id 가 있는 경우
        if(article.getId() != null){
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();

        Article target = articleRepository.findById(id).orElse(null);
        if (target == null || id != article.getId()) {
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }
        articleRepository.delete(target);
        return target;
    }


    public List<Article> createArticles(List<ArticleForm> dtos){
        List<Article> articleList = dtos.stream()
                .map(ArticleForm::toEntity)
                .collect(Collectors.toList());
        articleList.stream().forEach(
                article -> articleRepository.save(article)
        );

        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("조회 실패"));

        return articleList;
    }

}
