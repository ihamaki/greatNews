package wad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Picture extends AbstractPersistable<Long> {

    private String name;
    private String contentType;
    private Long size;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @OneToOne
    private News news;

}
