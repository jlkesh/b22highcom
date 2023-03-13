package dev.jlkeesh.controller;

import dev.jlkeesh.domains.Article;
import dev.jlkeesh.dto.dto.ArticleCreateDTO;
import dev.jlkeesh.dto.dto.ArticleDTO;
import dev.jlkeesh.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String articlesPage(Model model) {
        List<Article> articles = articleService.getAll();
        model.addAttribute("articles", articles);
        return "main";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("dto", new ArticleCreateDTO());
        return "article/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("dto") ArticleCreateDTO dto, BindingResult errors) {
        if (errors.hasErrors())
            return "article/create";
        MultipartFile file = dto.getPic();
        if (file.getSize() > 2 >> 21) {
            errors.rejectValue("pic", "", "file.size.exceeded");
            return "article/create";
        }
        articleService.create(dto);
        return "redirect:/";
    }
}
