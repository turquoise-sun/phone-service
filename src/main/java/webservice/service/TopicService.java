package webservice.service;

import webservice.model.Topic;

import java.util.Collection;

/**
 * Topic Service interface
 */
public interface TopicService  {

    Collection<Topic> findAll();

    Topic findOne(Long id);
}
