package com.example.FirstprojectStudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    // contents 만 있는 경우, title 만 있는 경우
    public void patch(Article article){
        if (article.title != null) {
            this.title = article.getTitle();
        }
        if (article.contents != null){
            this.contents = article.getContents();
        }
    }



}
