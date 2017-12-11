package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Category;
import wad.domain.News;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> getCategories(String categories) {
        List<Category> list = new ArrayList<>();
        String[] parts = categories.split(",");

        for (int i = 0; i < parts.length; i++) {
            String name = parts[i].trim().toLowerCase();
            Category category;
            if (categoryRepository.findByName(name) == null) {
                category = new Category(name);
                categoryRepository.save(category);
            } else {
                category = categoryRepository.findByName(name);
            }
            list.add(category);
        }
        return list;
    }

}
