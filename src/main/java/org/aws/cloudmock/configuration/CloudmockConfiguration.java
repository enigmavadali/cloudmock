package org.aws.cloudmock.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CloudmockConfiguration {

    @Bean("elasticRestTemplate")
    public RestTemplate getElasticRestTemplate(){
        return new RestTemplate();
    }

    @Bean("objMapper")
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
