package webservice.web.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import webservice.AbstractControllerTest;
import webservice.model.Inquiry;
import webservice.model.InquiryAttribute;
import webservice.model.Topic;
import webservice.service.InquiryService;
import webservice.service.TopicService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Inquiry Controller unit tests
 */

@Transactional
public class InquiryControllerTest extends AbstractControllerTest {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private TopicService topicService;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetInquiries() throws Exception {

        String uri = "/customers/{customerName}/inquiries";
        String customerName = "Jaroslav";

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri, customerName).accept(
                        MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

    @Test
    public void testGetInquiry() throws Exception {

        String uri = "/customers/{customerName}/inquiries/{id}";
        String customerName = "Jaroslav";
        Long id = new Long(1);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri, customerName, id).accept(
                        MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

    @Test
    public void testGetInquiryNotFound() throws Exception {

        String uri = "/customers/{customerName}/inquiries/{id}";
        String customerName = "Jaroslav";
        Long id = new Long(100);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri, customerName, id).accept(
                        MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 204", 204, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty",
                content.trim().length() == 0);

    }

    @Test
    public void testCreateInquiry() throws Exception {

        String uri = "/customers/{customerName}/inquiries";
        String customerName = "Jaroslav";

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
        inquiry.setCustomerName(customerName);
        inquiry.setTopic(topic);
        inquiry.setAttributes(attributes);

        String inputJson = super.mapToJson(inquiry);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post(uri, customerName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        Inquiry createdInquiry = super.mapFromJson(content, Inquiry.class);

        Assert.assertNotNull("failure - expected inquiry not null",
                createdInquiry);
        Assert.assertNotNull("failure - expected inquiry.inquiryId not null",
                createdInquiry.getInquiryId());
        Assert.assertEquals("failure - expected inquiry.description match", "test description",
                createdInquiry.getDescription());
        Assert.assertEquals("failure - expected inquiry.createDate match", date,
                createdInquiry.getCreateDate());
        Assert.assertEquals("failure - expected inquiry.customerName match", customerName,
                createdInquiry.getCustomerName());
        Assert.assertEquals("failure - expected inquiry.topic match", topic.toString(),
                createdInquiry.getTopic().toString());
        Assert.assertEquals("failure - expected inquiry.attributes.name match", attributes.iterator().next().getName(),
                createdInquiry.getAttributes().iterator().next().getName());
        Assert.assertEquals("failure - expected inquiry.attributes.value match", attributes.iterator().next().getValue(),
                createdInquiry.getAttributes().iterator().next().getValue());

    }

    @Test
    public void testUpdateInquiry() throws Exception {

        String uri = "/customers/{customerName}/inquiries/{id}";
        String customerName = "Jaroslav";
        Long id = new Long(1);

        Inquiry inquiry = inquiryService.findOne(id);
        String updatedDescription = inquiry.getDescription() + " test";
        inquiry.setDescription(updatedDescription);
        String inputJson = super.mapToJson(inquiry);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put(uri, customerName, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        Inquiry updatedInquiry = super.mapFromJson(content, Inquiry.class);

        Assert.assertNotNull("failure - expected inquiry not null",
                updatedInquiry);
        Assert.assertEquals("failure - expected inquiry.inquiryId unchanged",
                inquiry.getInquiryId(), updatedInquiry.getInquiryId());
        Assert.assertEquals("failure - expected updated inquiry.description text match",
                updatedDescription, updatedInquiry.getDescription());

    }

    @Test
    public void testDeleteInquiry() throws Exception {

        String uri = "/customers/{customerName}/inquiries/{id}";
        String customerName = "Jaroslav";
        Long id = new Long(1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, customerName, id))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 204", 204, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty",
                content.trim().length() == 0);

        Inquiry deletedInquiry = inquiryService.findOne(id);

        Assert.assertNull("failure - expected inquiry to be null",
                deletedInquiry);

    }


}
