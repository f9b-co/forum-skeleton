package fr.formation.training.forum;

import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.*;

import com.fasterxml.jackson.databind.*;

@Configuration
@EnableCaching
public class ForumConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
	configurer.addPathPrefix("/api",
		HandlerTypePredicate.forAnnotation(RestController.class));
    }

    @Bean
    public ModelMapper modelMapper() {
	ModelMapper mapper = new ModelMapper();
	return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
	ObjectMapper mapper = new ObjectMapper();
	mapper.findAndRegisterModules()
		.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	return mapper;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("technologies");
    }
}
