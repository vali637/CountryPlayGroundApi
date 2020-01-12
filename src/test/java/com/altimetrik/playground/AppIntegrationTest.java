package com.altimetrik.playground;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();


    private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    @Test
    public void testValidCountry() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(createUrl("/country/IND"), String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        

    } 
    @Test
    public void testInvalidCountry() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(createUrl("/country/YYY"), String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        

    } 
}
