package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Album;
import io.github.rerobika.rf1.domain.User;

import java.util.List;

public interface AlbumService {
    public List<Album> getAll();
    public void addAlbum(Album album);
    public void removeAlbum(Album album);
    public  Album getAlbum(long id);
    public User getOwner(Album album);
    public Album getNamedAlbum(User user, String name);

    public static final String POST_ALBUM = "post_picture";
    public static final String PROFILE_PICTURE_ALBUM = "profile_picture";
}
