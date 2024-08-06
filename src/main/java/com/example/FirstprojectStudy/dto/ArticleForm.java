package com.example.FirstprojectStudy.dto;

import com.example.FirstprojectStudy.controller.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String contents;


    public Article toEntity() {
        return new Article(null, title, contents);
    }
}
