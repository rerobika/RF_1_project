package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Picture;

import java.util.List;

public interface PictureService {
    public List<Picture> getAll();
    public void addPicture(Picture picture);
    public void removePicture(Picture picture);
    public  Picture getPicture(long id);
}
