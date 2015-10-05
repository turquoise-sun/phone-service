package webservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import webservice.AbstractTest;
import webservice.model.Topic;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Topic Service unit tests
 */

@Transactional
public class TopicServiceTest extends AbstractTest {

    @Autowired
    private TopicService topicService;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetTopics() {

        Collection<Topic> topics = topicService.findAll();

        Assert.assertNotNull("failure - expected not null", topics);
        Assert.assertEquals("failure - expected 2 elements", 2, topics.size());
    }

    @Test
    public void testGetTopic() {

        Long id = new Long(1);

        Topic topic = topicService.findOne(id);

        Assert.assertNotNull("failure - expected not null", topic);
        Assert.assertEquals("failure - expected topic.topicId match", id,
                topic.getTopicId());

    }

    @Test
    public void testGetTopicNotFound() {

        Long id = new Long(100);

        Topic topic = topicService.findOne(id);

        Assert.assertNull("failure - expected null", topic);

    }
}
