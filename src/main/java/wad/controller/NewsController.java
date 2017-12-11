package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.News;
import wad.domain.Picture;
import wad.repository.NewsRepository;
import wad.repository.PictureRepository;
import wad.service.AuthorService;
import wad.service.CategoryService;
import wad.service.NewsService;
import wad.service.PictureService;

import java.io.IOException;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PictureService pictureService;

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

    @DeleteMapping("/news/{id}")
    public String deleteSingle(@PathVariable Long id) {
        newsRepository.deleteById(id);
        return "redirect:/news";
    }

//    @GetMapping(path = "/news/{id}/picture", produces = "image/png")
//    @ResponseBody
//    @Transactional
//    public byte[] get(@PathVariable Long id) {
//        News news = newsRepository.getOne(id);
//        return pictureRepository.getOne(news.getPicture().getId()).getContent();
//    }

    @GetMapping(path = "/news/{id}/picture", produces = "image/png")
    @ResponseBody
    @Transactional
    public ResponseEntity<byte[]> getPicture(@PathVariable Long id) {
        News news = newsRepository.getOne(id);
        Picture picture = pictureRepository.getOne(news.getPicture().getId());

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(picture.getContentType()));
        headers.setContentLength(picture.getSize());

        return new ResponseEntity<>(picture.getContent(), headers, HttpStatus.CREATED);
    }

    @GetMapping("/news/add")
    public String addArticle() {
        return "newarticle";
    }

    @Transactional
    @PostMapping("/news/add")
    public String postNewsItem(@RequestParam String title,
                         @RequestParam String lead,
                         @RequestParam String text,
                         @RequestParam String authors,
                         @RequestParam String categories,
                         @RequestParam("file") MultipartFile file) throws IOException {

        Long newsId = newsRepository.save(new News(title, lead, text)).getId();
        newsService.setAuthors(newsId, authorService.getAuthors(authors));
        newsService.setCategories(newsId, categoryService.getCategories(categories));
        newsService.setPicture(newsId, pictureService.getPicture(file));
        return "redirect:/news";
    }

    @GetMapping("/news/{id}/edit")
    public String getEditPage(Model model, @PathVariable Long id) {
        model.addAttribute("news", newsRepository.getOne(id));
        return "edit";
    }

    @PostMapping("/news/{id}/edit")
    public String editSingle(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String lead,
                             @RequestParam String text,
                             @RequestParam String authors,
                             @RequestParam String categories,
                             @RequestParam("file") MultipartFile picture) throws IOException {
        News news = newsRepository.getOne(id);
        newsService.editNews(news, title, lead, text);
        newsService.setCategories(news.getId(), categoryService.getCategories(categories));
        newsService.setAuthors(news.getId(), authorService.getAuthors(authors));
        newsService.setPicture(news.getId(), pictureService.getPicture(picture));
        newsRepository.save(news);
        return "redirect:/news/{id}";
    }

}
