package wad.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    private List<String> errors;

    public List<String> validateNews(String title, String lead, String text,
                                     String authors, String categories) {
        this.errors = new ArrayList();
        validateTitle(title);
        validateLead(lead);
        validateText(text);
        validateAuthors(authors);
        validateCategories(categories);
        return this.errors;
    }

    private void validateTitle(String title) {
        if (title.isEmpty()) {
            errors.add("Title is missing");
        } else if (title.length() > 200) {
            errors.add("Title is too long");
        }
    }

    private void validateLead(String lead) {
        if (lead.isEmpty()) {
            errors.add("Lead is missing");
        } else if (lead.length() > 500) {
            errors.add("Lead is too long");
        }
    }

    private void validateText(String text) {
        if (text.isEmpty()) {
            errors.add("Text is missing");
        } else if (text.length() > 5000) {
            errors.add("Text is too long");
        }
    }

    private void validateAuthors(String authors) {
        if (authors.isEmpty()) {
            errors.add("Author is missing");
        }
    }

    private void validateCategories(String categories) {
        if (categories.isEmpty()) {
            errors.add("Category is missing");
        }
    }
}
