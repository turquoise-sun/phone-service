package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.model.Topic;

/**
 * Topic Repository interface
 */

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
