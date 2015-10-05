package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.model.Topic;
import webservice.repository.TopicRepository;

import java.util.Collection;

/**
 * Topic Service implementation
 */

@Service
public class TopicServiceBean implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Collection<Topic> findAll() {
        Collection<Topic> topics = topicRepository.findAll();
        return topics;
    }

    @Override
    public Topic findOne(Long id) {
        Topic topic = topicRepository.findOne(id);
        return topic;
    }
}
