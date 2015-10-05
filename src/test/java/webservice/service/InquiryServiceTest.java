package webservice.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import webservice.AbstractTest;
import webservice.model.Inquiry;
import webservice.model.InquiryAttribute;
import webservice.model.Topic;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Inquiry Service unit tests
 */

@Transactional
public class InquiryServiceTest extends AbstractTest{

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private TopicService topicService;


    @Test
    public void testGetInquiries() {

        Collection<Inquiry> inquiries = inquiryService.findAll();

        Assert.assertNotNull("failure - expected not null", inquiries);
        Assert.assertEquals("failure - expected 1 element", 1,
                inquiries.size());

    }

    @Test
    public void testGetInquiry() {

        Long id = new Long(1);

        Inquiry inquiry = inquiryService.findOne(id);

        Assert.assertNotNull("failure - expected not null", inquiry);
        Assert.assertEquals("failure - expected inquiry.inquiryId match", id,
                inquiry.getInquiryId());

    }

    @Test
    public void testGetInquiryNotFound() {

        Long id = new Long(100);

        Inquiry inquiry = inquiryService.findOne(id);

        Assert.assertNull("failure - expected null", inquiry);

    }

    @Test
    public void testCreateInquiry() {

        Collection<InquiryAttribute> attributes = new ArrayList<>();
        InquiryAttribute attribute = new InquiryAttribute();
        attribute.setName("test name");
        attribute.setValue("test value");
        attributes.add(attribute);

        Topic topic = topicService.findOne(new Long(1));
        Date date = new Date();

        Inquiry inquiry = new Inquiry();
        inquiry.setDescription("test description");
        inquiry.setCreateDate(date);
        inquiry.setCustomerName("test customer");
        inquiry.setTopic(topic);
        inquiry.setAttributes(attributes);


        Inquiry createdInquiry = inquiryService.create(inquiry);

        Assert.assertNotNull("failure - expected inquiry not null",
                createdInquiry);
        Assert.assertNotNull("failure - expected inquiry.inquiryId not null",
                createdInquiry.getInquiryId());
        Assert.assertEquals("failure - expected inquiry.description match", "test description",
                createdInquiry.getDescription());
        Assert.assertEquals("failure - expected inquiry.createDate match", date,
                createdInquiry.getCreateDate());
        Assert.assertEquals("failure - expected inquiry.customerName match", "test customer",
                createdInquiry.getCustomerName());
        Assert.assertEquals("failure - expected inquiry.topic match", topic,
                createdInquiry.getTopic());
        Assert.assertEquals("failure - expected inquiry.attributes match", attributes,
                createdInquiry.getAttributes());


        Collection<Inquiry> inquiries = inquiryService.findAll();

        Assert.assertEquals("failure - expected 2 inquiries", 2,
                inquiries.size());

    }

    @Test
    public void testCreateInquiryWithId() {


        Collection<InquiryAttribute> attributes = new ArrayList<>();
        InquiryAttribute attribute = new InquiryAttribute();
        attribute.setName("test name");
        attribute.setValue("test value");
        attributes.add(attribute);

        Topic topic = topicService.findOne(new Long(1));
        Date date = new Date();

        Inquiry inquiry = new Inquiry();
        inquiry.setDescription("test description");
        inquiry.setCreateDate(date);
        inquiry.setCustomerName("test customer");
        inquiry.setTopic(topic);
        inquiry.setAttributes(attributes);

        inquiry.setInquiryId(new Long(100));

        Inquiry createdInquiry = inquiryService.create(inquiry);

        Assert.assertNull("failure - expected inquiry match null", createdInquiry);

    }

    @Test
    public void testUpdateInquiry() {

        Long id = new Long(1);

        Inquiry inquiry = inquiryService.findOne(id);

        Assert.assertNotNull("failure - expected inquiry not null", inquiry);

        String updatedDescription = inquiry.getDescription() + " test";
        inquiry.setDescription(updatedDescription);
        Inquiry updatedInquiry = inquiryService.update(inquiry);

        Assert.assertNotNull("failure - expected updated inquiry not null",
                updatedInquiry);
        Assert.assertEquals("failure - expected updated inquiry id unchanged",
                id, updatedInquiry.getInquiryId());
        Assert.assertEquals("failure - expected updated inquiry.description match",
                updatedDescription, updatedInquiry.getDescription());

    }

    @Test
    public void testUpdateInquiryNotFound() {

        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryId(new Long(100));

        Inquiry updatedInquiry = inquiryService.update(inquiry);

        Assert.assertNull("failure - expected inquiry null", updatedInquiry);

    }

    @Test
    public void testDeleteInquiry() {

        Long id = new Long(1);

        Inquiry inquiry = inquiryService.findOne(id);

        Assert.assertNotNull("failure - expected inquiry not null", inquiry);

        inquiryService.delete(id);

        Collection<Inquiry> inquiries = inquiryService.findAll();

        Assert.assertEquals("failure - expected 0 inquiries", 0,
                inquiries.size());

        Inquiry deletedInquiry = inquiryService.findOne(id);

        Assert.assertNull("failure - expected inquiry to be deleted",
                deletedInquiry);

    }


}
