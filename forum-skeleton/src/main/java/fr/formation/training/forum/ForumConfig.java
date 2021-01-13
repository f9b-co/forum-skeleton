package fr.formation.training.forum;

import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;

@Configuration
@EnableCaching
// @EnableScheduling
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
	// Activate modules such as java.time support:
	mapper.findAndRegisterModules();
	// Change Java 8 (java.time) serialization format
	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	// How to access data in JSON/Java objects (fields and/or getters)
	mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
	// mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
	// mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	return mapper;
    }

    @Bean
    public CacheManager cacheManager() {
	return new ConcurrentMapCacheManager("technologies");
    }
    // @Scheduled(fixedRate = 30000)
    // public void clearCache() {
    // cacheManager().getCache("technologies").clear();
    // }
}
