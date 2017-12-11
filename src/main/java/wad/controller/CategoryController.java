package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wad.repository.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String getAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String getOneCategory(Model model, @PathVariable Long id) {
        model.addAttribute("category", categoryRepository.getOne(id));
        model.addAttribute("newsList", categoryRepository.getOne(id).getNewsList());
        return "singlecategory";
    }
}
