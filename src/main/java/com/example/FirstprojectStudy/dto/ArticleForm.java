package com.example.FirstprojectStudy.dto;

import com.example.FirstprojectStudy.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String contents;


    public Article toEntity() {
        return new Article(id, title, contents);
    }
}
