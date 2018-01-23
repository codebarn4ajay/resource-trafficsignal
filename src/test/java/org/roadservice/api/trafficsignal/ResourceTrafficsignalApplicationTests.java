package org.roadservice.api.trafficsignal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ResourceTrafficsignalApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
	@Test
    public void contextLoads() {
        assertThat(webApplicationContext, is(notNullValue()));

    }
}
