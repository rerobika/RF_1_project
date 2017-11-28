package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByToIdOrderByDateDesc(Long fromId);

    List<Message> findByToIdAndFromIdOrderByDateDesc(Long fromId, Long toId);
}
