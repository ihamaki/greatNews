package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wad.domain.News;
import wad.repository.NewsRepository;
import wad.service.AuthorService;
import wad.service.CategoryService;
import wad.service.NewsService;
import wad.service.ValidationService;

import java.util.List;

@Controller
@Transactional
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/news")
    public String getLatest(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "published");
        model.addAttribute("newsList", newsRepository.findAll(pageable));
        return "news";
    }

    @GetMapping("/news/{id}")
    public String getSingle(Model model, @PathVariable Long id) {
        model.addAttribute("news", newsRepository.getOne(id));
        return "single";
    }

    @DeleteMapping("/news/{id}")
    public String deleteSingle(@PathVariable Long id) {
        newsRepository.deleteById(id);
        return "redirect:/news";
    }

    @GetMapping("/news/add")
    public String addArticle() {
        return "newarticle";
    }


    @PostMapping("/news/add")
    public String postNewsItem(Model model,
                               @RequestParam String title,
                               @RequestParam String lead,
                               @RequestParam String text,
                               @RequestParam String authors,
                               @RequestParam String categories) {

        List<String> errorMessages = validationService.validateNews(title, lead, text, authors, categories);
        if (!errorMessages.isEmpty()) {
            model.addAttribute("errors", errorMessages);
            return "newarticle";
        }

        News news = new News(title, lead, text);
        newsService.setCategories(news, categoryService.getCategories(categories));
        newsService.setAuthors(news, authorService.getAuthors(authors));
        newsRepository.save(news);
        return "redirect:/news";
    }

    @GetMapping("/news/{id}/edit")
    public String getEditPage(Model model, @PathVariable Long id) {
        model.addAttribute("news", newsRepository.getOne(id));
        return "edit";
    }

    @PostMapping("/news/{id}/edit")
    public String editSingle(Model model,
                             @PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String lead,
                             @RequestParam String text,
                             @RequestParam String authors,
                             @RequestParam String categories) {

        List<String> errorMessages = validationService.validateNews(title, lead, text, authors, categories);
        if (!errorMessages.isEmpty()) {
            model.addAttribute("errors", errorMessages);
            return "redirect:/news/{id}/edit";
        }

        News news = newsRepository.getOne(id);
        newsService.editNews(news, title, lead, text);
        newsService.setCategories(news, categoryService.getCategories(categories));
        newsService.setAuthors(news, authorService.getAuthors(authors));
        newsRepository.save(news);
        return "redirect:/news/{id}";
    }

}
