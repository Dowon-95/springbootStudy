package com.example.FirstprojectStudy.repository;

import com.example.FirstprojectStudy.entity.Article;
import com.example.FirstprojectStudy.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("id로 조회")
    void fideByArticleId() {
        Long articleId = 4L;

        List<Comment> comments = commentRepository.findByArticleId(articleId);

        Article article = new Article(4L,"당신의 인생 영화는?","댓글 고");
        Comment comment1 = new Comment(1L,article, "Park", "굿 윌 헌팅");
        Comment comment2 = new Comment(2L,article, "Kim", "아이 엠 샘");
        Comment comment3 = new Comment(3L,article, "Choi", "쇼생크 탈출");

        List<Comment> expected = Arrays.asList(comment1,comment2,comment3);

        assertEquals(expected.toString(), comments.toString());
    }




    @Test
    @DisplayName("닉네임으로 조회")
    void findByNickname(){
        List<Comment> comments = commentRepository.findByNickname("Park");

        Comment comment1 = new Comment(1L, new Article(4L,"당신의 인생 영화는?", "댓글 고"), "Park","굿 윌 헌팅");
        Comment comment2 = new Comment(4L, new Article(5L,"당신의 소울 푸드는?", "댓글 고고"), "Park","치킨");
        Comment comment3 = new Comment(7L, new Article(6L,"당신의 취미는?", "댓글 고고고"), "Park","조깅");

        assertEquals(Arrays.asList(comment1,comment2,comment3).toString(), comments.toString());
    }
}