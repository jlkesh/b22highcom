package dev.jlkeesh.controller;

import dev.jlkeesh.domains.Article;
import dev.jlkeesh.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Article> articles = articleService.getAll();
        model.addAttribute("articles", articles);
        return "main";
    }
}
