package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.News;
import wad.repository.NewsRepository;

import java.time.LocalDateTime;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public String getAll(Model model) {
        model.addAttribute(newsRepository.findAll());
        return "news";
    }

    @PostMapping("/news")
    public String postNewsItem(@RequestParam String title, @RequestParam String lead, @RequestParam String text) {
        News news = new News(title, lead, text, LocalDateTime.now());
        newsRepository.save(news);
        return "redirect:/news";
    }

}
