package com.example.FirstprojectStudy.repository;

import com.example.FirstprojectStudy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //natived sql
    @Query(value = "SELECT * FROM comment WHERE article_id =:articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // mapping file

    List<Comment> findByNickname(String nickname);
}
