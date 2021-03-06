package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Album;
import io.github.rerobika.rf1.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album getAlbumByOwnerAndName(User user, String name);
}
