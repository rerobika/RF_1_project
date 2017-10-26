package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> getAll();
    public void addAlbum(Album album);
    public void removeAlbum(Album album);
    public  Album getAlbum(long id);
}
