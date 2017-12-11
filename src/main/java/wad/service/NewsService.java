package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Author;
import wad.domain.Category;
import wad.domain.News;
import wad.repository.AuthorRepository;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;

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

    @Transactional
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
    public void setCategories(News news, List<Category> categories) {
        for (Category category : categories) {
            news.addCategory(category);
            category.addNews(news);
            newsRepository.save(news);
            categoryRepository.save(category);
        }
    }

    @Transactional
    public void setAuthors(News news, List<Author> authors) {
        for (Author author : authors) {
            news.addAuthor(author);
            author.addNews(news);
            newsRepository.save(news);
            authorRepository.save(author);
        }
    }
}
