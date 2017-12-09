package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.News;
import wad.repository.NewsRepository;
import wad.service.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String getAll(Model model) {
        model.addAttribute("newsList", newsRepository.findAll());
        return "news";
    }

    @GetMapping("/news/{id}")
    public String getSingle(Model model, @PathVariable Long id) {
        model.addAttribute("news", newsRepository.getOne(id));
        return "single";
    }

    @GetMapping("/news/add")
    public String addArticle() {
        return "newarticle";
    }


    @PostMapping("/news/add")
    public String postNewsItem(@RequestParam String title, @RequestParam String lead, @RequestParam String text,
                               @RequestParam String authors, @RequestParam String categories) {
        News news = new News(title, lead, text);
        newsRepository.save(news);
        return "redirect:/news";
    }

}
