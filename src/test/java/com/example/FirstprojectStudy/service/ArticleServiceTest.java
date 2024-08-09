package com.example.FirstprojectStudy.service;

import com.example.FirstprojectStudy.dto.ArticleForm;
import com.example.FirstprojectStudy.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Test
    void index() {

        // 검증용 데이터
        Article article1 = new Article(1L, "제목1", "1111");
        Article article2 = new Article(2L, "제목2", "2222");
        Article article3 = new Article(3L, "제목3", "3333");


        // 조회
        List<Article> articleList = articleService.index();

        // 검증용 데이터와 죄회된 데이터 비교
        assertEquals(Arrays.asList(article1,article2,article3).toString() ,articleList.toString());
    }

    @Test
    void show_success_exist_id() {
        Long id = 1L;
        Article expected = new Article(id, "제목1", "1111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail_notExist_id() {
        Long id = -1L;
        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void create_exist_titleAndContents() {
        ArticleForm dto = new ArticleForm(null, "제목4", "4444");
        Article expected = new Article(4L, "제목4", "4444");

        Article article = articleService.create(dto);

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_exist_title(){
        ArticleForm dto = new ArticleForm(null, "제목4", null);
        Article expected = new Article(4L, "제목4", null);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_exist_contents(){
        ArticleForm dto = new ArticleForm(null, null, "4444");
        Article expected = new Article(4L, null, "4444");

        Article article = articleService.create(dto);

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_exist_id(){
        ArticleForm dto = new ArticleForm(4L, null, null);
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_exist_id_title_content() {

        ArticleForm dto = new ArticleForm(1L, "제목1", "1111");
        Article expected = new Article(1L, "제목1", "1111");

        Article article = articleService.update(1L, dto);

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_notExist_id_title_content() {

        ArticleForm dto = new ArticleForm(-1L, "제목1", "1111");
        Article expected =null;

        Article article = articleService.update(-1L, dto);

        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void delete_exist_id() {
        Article article = new Article(1L, "제목1", "1111");

        Article expected = articleService.delete(1L);

        assertEquals(expected, article);
    }

    @Test
    void delete_notExist_id() {
        Article article = new Article(-1L, "제목1", "1111");
        Article expected = null;

        assertEquals(expected, article);

    }

}