package org.newsportal.rest.controller;

import org.newsportal.service.ArticleService;
import org.newsportal.service.model.Article;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news-portal")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAll().orElseThrow());
    }

    @GetMapping(value = "/article_id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getById(id).orElseThrow());
    }

    @GetMapping(value = "/article_title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(articleService.getByTitle(title).orElseThrow());
    }

    @GetMapping(value = "/article_byUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getByUserId(id).orElseThrow());
    }

    @PatchMapping(value = "/article_id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateById(@PathVariable Long id, @RequestBody Article article) {
        articleService.updateArticleById(id, article);
    }

    @DeleteMapping(value = "/article_id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable Long id) {
        articleService.deleteArticleById(id);
    }

}
