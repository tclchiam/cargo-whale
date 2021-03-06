package com.cargowhale.docker.config.docker;

import com.cargowhale.docker.client.core.DockerErrorHandler;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import com.cargowhale.docker.client.core.UnixComponentsClientHttpRequestFactory;
import com.cargowhale.docker.client.logs.LogStreamMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.docker.client.ObjectMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties({DockerProperties.class})
public class DockerRestTemplateConfiguration {

    private final DockerProperties properties;
    private final DockerErrorHandler errorHandler;

    @Autowired
    public DockerRestTemplateConfiguration(final DockerProperties properties, final DockerErrorHandler errorHandler) {
        this.properties = properties;
        this.errorHandler = errorHandler;
    }

    @Bean
    public DockerRestTemplate dockerRestTemplate() {
        String rootUri = this.properties.getUri();

        UnixComponentsClientHttpRequestFactory requestFactory = new UnixComponentsClientHttpRequestFactory(rootUri);
        RootUriTemplateHandler uriTemplateHandler = new RootUriTemplateHandler(rootUri);

        List<HttpMessageConverter<?>> messageConverters = Arrays.asList(
            new MappingJackson2HttpMessageConverter(getDockerObjectMapper()),
            new LogStreamMessageConverter(),
            new StringHttpMessageConverter()
        );

        DockerRestTemplate restTemplate = new DockerRestTemplate(this.errorHandler, requestFactory, messageConverters, uriTemplateHandler);
        restTemplate.setUriTemplateHandler(uriTemplateHandler);

        return restTemplate;
    }

    private static ObjectMapper getDockerObjectMapper() {
        ObjectMapperProvider provider = new ObjectMapperProvider();
        return provider.getContext(ObjectMapper.class);
    }
}
