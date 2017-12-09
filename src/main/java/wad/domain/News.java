package wad.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News extends AbstractPersistable<Long> {

    private String title;
    private String lead;
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
}
