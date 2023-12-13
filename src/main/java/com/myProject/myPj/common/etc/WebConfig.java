package com.myProject.myPj.common.etc;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
    MappingJackson2JsonView jsonView() {
    	return new MappingJackson2JsonView();
    }
	
//	 private final String resourcePath;
	
//	 public WebConfig(@Value("${custom.imgPath}") String resourcePath) {
//	    this.resourcePath = resourcePath;
//	 }
//    private String connectPath = "/outSource/**";
//
//    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    	
//        registry.addResourceHandler(connectPath)
//                .addResourceLocations(resourcePath);
//    }
    
}