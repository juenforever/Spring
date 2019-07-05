package kr.or.ddit.config.spring;

import org.apache.tiles.locale.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

import kr.or.ddit.view.ExcelDownloadView;
import kr.or.ddit.view.ProfileView;

@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false, 
			includeFilters = {@Filter(type = FilterType.ANNOTATION,
			classes = {Controller.class, ControllerAdvice.class})})
@EnableWebMvc
@Configuration
public class ApplicationContext extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean public ViewResolver InternalResourceViewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		
		return irvr;
	}
	
	@Bean ViewResolver beanNameViewResolver() {
		BeanNameViewResolver bnvr = new BeanNameViewResolver();
		bnvr.setOrder(2);
		
		return bnvr;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
	
		tilesConfigurer.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-config.xml");
		return tilesConfigurer;
	}
	
	@Bean 
	public ViewResolver tielsViewResolver() {
		TilesViewResolver tvr = new TilesViewResolver();
		tvr.setOrder(1);
		tvr.setViewClass(TilesView.class);
		return tvr;
	}
	
	@Bean
	public View jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
	public View profileView() {
		return new ProfileView();
	}
	
	@Bean
	public View userExcelView() {
		return new ExcelDownloadView();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSizePerFile(1024*1024*3);
		cmr.setMaxUploadSize(1024*1024*3*5);
		return cmr;
	}
	
	@Bean 
	public MessageSource  messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		messageSource.addBasenames("classpath:kr/or/ddit/msg/error","classpath:kr/or/ddit/msg/msg");
		
		return messageSource;
	}
	
	
}
