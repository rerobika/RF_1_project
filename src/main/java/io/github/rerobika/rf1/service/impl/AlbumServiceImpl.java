package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Album;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.service.AlbumService;
import io.github.rerobika.rf1.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRep;


    @Override
    public List<Album> getAll()
    {
        if(albumRep.count()!=0)
            return (List<Album>) albumRep.findAll();
        else return  new ArrayList<Album>();
    }

    @Override
    public void addAlbum(Album album) {albumRep.save(album);}

    @Override
    public void removeAlbum(Album album) {albumRep.delete(album);}

    @Override
    public Album getAlbum(long id) {
        return albumRep.findOne(id);
    }

    @Override
    public User getOwner(Album album) {
        return album.getOwner();
    }

    @Override
    public Album getNamedAlbum(User user, String name) {
        return albumRep.getAlbumByOwnerAndName(user, name);
    }
}
