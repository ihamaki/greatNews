package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors(String authors) {
        List<Author> list = new ArrayList<>();
        String[] parts = authors.split(",");
        for (int i = 0; i < parts.length; i++) {
            String name = parts[i].trim();
            Author author;
            if (authorRepository.findByName(name) == null) {
                author = new Author(name);
                authorRepository.save(author);
            } else {
                author = authorRepository.findByName(name);
            }
            list.add(author);
        }
        return list;
    }
}
