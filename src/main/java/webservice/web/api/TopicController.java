package webservice.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webservice.model.Topic;
import webservice.service.TopicService;

import java.util.Collection;

/**
 * Topic Controller
 */


@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/topics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Topic>> getTopics() {
        Collection<Topic> topics = topicService.findAll();
        return new ResponseEntity<Collection<Topic>>(topics, HttpStatus.OK);
    }
}
