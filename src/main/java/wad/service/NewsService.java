package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Category;
import wad.domain.News;
import wad.repository.AuthorRepository;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void setCategories(News news, List<Category> categories) {
        for (Category category : categories) {
            news.addCategory(category);
            category.addNews(news);
            newsRepository.save(news);
            categoryRepository.save(category);
        }
    }

    public void setAuthors(News news, List<Author> authors) {
        for (Author author : authors) {
            news.addAuthor(author);
            author.addNews(news);
            newsRepository.save(news);
            authorRepository.save(author);
        }
    }
}
