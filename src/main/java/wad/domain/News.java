package wad.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;

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
    
}
