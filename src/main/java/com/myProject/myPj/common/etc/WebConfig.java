package com.myProject.myPj.common.etc;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
    MappingJackson2JsonView jsonView() {
    	return new MappingJackson2JsonView();
    }
	@Autowired
	private DefaultInterceptor defaultInterceptor;

	    
	private final String resourcePath;

	public WebConfig(@Value("${custom.imgPath}") String resourcePath) {
	    this.resourcePath = resourcePath;
	 }
   private String connectPath = "/outSource/**";

   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
   	
       registry.addResourceHandler(connectPath)
               .addResourceLocations(resourcePath);
   }
   
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       // 인터셉터를 등록하는 메서드
       // CustomInterceptor를 등록하고, 모든 URL에 대해 인터셉터를 적용하도록 설정
       registry.addInterceptor(defaultInterceptor).addPathPatterns("/**");
   }
    
}