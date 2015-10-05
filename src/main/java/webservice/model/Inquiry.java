package webservice.model;

import javax.persistence.*;
import java.util.*;

/**
 * Inquiry entity. Has relationship with Inquiry Attribute and Topic entity
 */


@Entity
public class Inquiry {

    @Id
    @GeneratedValue
    @Column(name="INQUIRY_ID")
    private Long inquiryId;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CREATE_DATE")
    private Date createDate;

    @Column(name="CUSTOMER_NAME")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "TOPIC_ID")
    private Topic topic;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "INQUIRY_ID")
    private Collection<InquiryAttribute> attributes;


    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Collection<InquiryAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<InquiryAttribute> attributes) {
        this.attributes = attributes;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
