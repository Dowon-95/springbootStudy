package com.example.FirstprojectStudy.controller;

import com.example.FirstprojectStudy.entity.Article;
import com.example.FirstprojectStudy.dto.ArticleForm;
import com.example.FirstprojectStudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id : " + id);

        // id 로 글 조회
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 조회된 글을 Model 에 등록
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String showAll(Model model) {
        // 전체 글 목록 조회
        List<Article> articleList = articleRepository.findAll();

        // 모델에 데이터 등록
        model.addAttribute("articleList", articleList);
        // 뷰페이지 전환
        return "articles/showAll";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target!=null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/"+ articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepository.findById(id).orElse(null);
        if(target!=null){
            articleRepository.delete((target));
            rttr.addFlashAttribute("msg","삭제되었습니다");
        }
        return "redirect:/articles";
    }
}
