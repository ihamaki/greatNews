package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Author;
import wad.domain.Category;
import wad.domain.News;
import wad.domain.Picture;
import wad.repository.AuthorRepository;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;
import wad.repository.PictureRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PictureRepository pictureRepository;


    public void editNews(News news, String title, String lead, String text) {
        news.setTitle(title);
        news.setLead(lead);
        news.setText(text);
        news.setPublished(LocalDateTime.now());
        news.getCategories().clear();
        news.getAuthors().clear();
        newsRepository.save(news);
    }

    @Transactional
    public void setCategories(Long newsId, List<Category> categories) {
        News news = newsRepository.getOne(newsId);
        for (Category category : categories) {
            news.addCategory(category);
            category.addNews(news);
            newsRepository.save(news);
            categoryRepository.save(category);
        }
    }

    @Transactional
    public void setAuthors(Long newsId, List<Author> authors) {
        News news = newsRepository.getOne(newsId);
        for (Author author : authors) {
            news.addAuthor(author);
            author.addNews(news);
            newsRepository.save(news);
            authorRepository.save(author);
        }
    }

    @Transactional
    public void setPicture(Long newsId, Picture picture) {
        News news = newsRepository.getOne(newsId);
        news.setPicture(picture);
        picture.setNews(news);
        newsRepository.save(news);
        pictureRepository.save(picture);
    }
}
