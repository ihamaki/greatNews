package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

}
