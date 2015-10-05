package webservice.web.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import webservice.AbstractControllerTest;

import javax.transaction.Transactional;

/**
 * Topic Controller unit tests
 */

@Transactional
public class TopicControllerTest extends AbstractControllerTest {


    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void testGetTopics() throws Exception {

        String uri = "/topics";

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }
}
