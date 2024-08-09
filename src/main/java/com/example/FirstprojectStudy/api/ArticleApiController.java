package com.example.FirstprojectStudy.api;

import com.example.FirstprojectStudy.dto.ArticleForm;
import com.example.FirstprojectStudy.entity.Article;
import com.example.FirstprojectStudy.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @PostMapping("api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article crated = articleService.create(dto);

        return (crated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(crated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id,dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


    }
/*
    @Autowired
    private ArticleRepository articleRepository;

    // 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 게시글 등록
    @PostMapping("api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // 게시글 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 엔터티 생성
        Article article = dto.toEntity();
        // DB 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 1. 조회를 했지만 없는 경우 2. 조회 id 와 본문 id 가 다른 경우
        if (target == null || !id.equals(article.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 저장후 결과를 response
        target.patch(article);
        Article update = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(update);

    }

    // 게시글 삭제
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        // 검색
        Article target = articleRepository.findById(id).orElse(null);

        // 없는 게시글의 경우 응답 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

 */
}
