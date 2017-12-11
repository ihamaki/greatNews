package wad.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import sun.security.util.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News extends AbstractPersistable<Long> {

    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    @NotBlank
    @Size(min = 1, max = 500)
    private  String lead;

    @NotBlank
    @Size(min = 1, max = 5000)
    private String text;

    private LocalDateTime published;

    @ManyToMany
    private List<Author> authors;

    @ManyToMany
    private List<Category> categories;

    public News(String title, String lead, String text) {
        this.title = title;
        this.lead = lead;
        this.text = text;
        this.published = LocalDateTime.now();
        this.authors = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void addAuthor(Author author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
    }
}
