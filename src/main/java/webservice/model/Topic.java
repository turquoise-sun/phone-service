package webservice.model;

import javax.persistence.*;

/**
 * Created by TurquoiseSun on 01.10.2015.
 */

@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @GeneratedValue
    @Column(name = "TOPIC_ID")
    private Long topicId;

    @Column(name = "NAME")
    private String name;

    public Topic() {

    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", name='" + name + '\'' +
                '}';
    }
}
