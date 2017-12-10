package wad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<News> newsList;

    public Author(String name) {
        this.name = name;
        this.newsList = new ArrayList<>();
    }

    public void addNews(News news) {
        if (!newsList.contains(news)) {
            newsList.add(news);
        }
    }
}
