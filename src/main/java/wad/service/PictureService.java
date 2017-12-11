package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.Picture;
import wad.repository.PictureRepository;

import java.io.IOException;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture getPicture(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        picture.setName(file.getOriginalFilename());
        picture.setContentType(file.getContentType());
        picture.setSize(file.getSize());
        picture.setContent(file.getBytes());
        pictureRepository.save(picture);
        return picture;
    }
}
