package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Picture;
import io.github.rerobika.rf1.service.PictureService;
import io.github.rerobika.rf1.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRep;

    @Override
    public List<Picture> getAll()
    {
        if(pictureRep.count()!=0)
            return (List<Picture>) pictureRep.findAll();
        else return  new ArrayList<Picture>();
    }

    @Override
    public void addPicture(Picture picture) {pictureRep.save(picture);}

    @Override
    public void removePicture(Picture picture) {pictureRep.delete(picture);}

    @Override
    public Picture getPicture(long id) {return pictureRep.findOne(id);}
}
