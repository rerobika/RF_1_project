package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Collection<Post> findByParent(Post parent);
    Collection<Post> findByFromAndParentIsNull(User user);
    Collection<Post> findByToAndParentIsNullOrderByDateDesc (User user);
    Collection<Post> findAllByParentIsNull();
    Collection<Post> findByFromAndParentIsNullOrderByDateDesc(User user);
}
