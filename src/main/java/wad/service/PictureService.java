package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.News;
import wad.domain.Picture;
import wad.repository.NewsRepository;
import wad.repository.PictureRepository;

import java.io.IOException;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Transactional
    public Picture setPicture(MultipartFile file, Long id) throws IOException {
        Picture picture = new Picture();

        try {
            picture.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        picture.setSize(file.getSize());
        picture.setContentType(file.getContentType());
        picture.setName(file.getName());

        pictureRepository.save(picture);
        return picture;
    }

    @Transactional
    public Picture getPicture(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        picture.setName(file.getName());
        picture.setContentType(file.getContentType());
        picture.setSize(file.getSize());
        picture.setContent(file.getBytes());
        pictureRepository.save(picture);
        return picture;
    }
}
