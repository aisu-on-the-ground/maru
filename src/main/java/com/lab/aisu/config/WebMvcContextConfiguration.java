package com.lab.aisu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration // 이 .java 파일이 설정파일임을 명시
@EnableWebMvc // 기본적인 설정들을 자동으로 받아오는 어노테이션
@ComponentScan(basePackages = { "com.lab.aisu.controller", "com.lab.aisu.restcontroller" })
public class WebMvcContextConfiguration implements WebMvcConfigurer {

	// DispatcherServlet이 실행될 때 읽어들이는 설정 파일

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/bootstrap/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/").setCachePeriod(31556926);
		registry.addResourceHandler("/icon/**").addResourceLocations("classpath:/icon/").setCachePeriod(31556926);
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/fonts/").setCachePeriod(31556926);
//		모든 요청을 받다보니, 컨트롤러에 URL 매핑되지 않은 요청이 들어올 수도 있다. 대표적인 예시가 CSS, 이미지, JS 등 정적인 자원을
//		요청했을 때이다. 만약 그러한 요청이 들어오면 애플리케이션 루트 디렉토리 아래 있는 각각의 디렉토리(/css, /img, /js)에서 해당
//		자원을 찾을 수 있도록 설정. 이 부분이 없다면 모두 컨트롤러가 가진 RequestMapping에서 찾으려고 하면서 오류를 발생시킨다.
//		반드시 필요한 부분.
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
		configurer.enable();
//		파라미터로 전달받은 DefaultServletHandlerConfigurer객체의 enable() 메소드를 호출하여
//		DefaultServletHandler를 사용하도록 해주는 부분.
//		매핑 정보가 없는 URL은 DefaultServletHttpRequestHandler가 처리하는데,
//		DefaultServletHttpRequestHandler는 WAS의 DefaultServlet에게 해당 일을 넘기게 된다.
//		그러면 DefaultServlet이 static한 자원을 읽어서 보여주게 된다.
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry);
		System.out.println("addViewControllers가 호출됩니다. ");
		registry.addViewController("/").setViewName("login");
//		특정 URL에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있도록 해주는 부분.
//		"/"라는 요청이 들어오면 "login"이라는 이름의 뷰를 보여주도록 한다.
	}

//	@Bean
//	public InternalResourceViewResolver getInternalResourceViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewNames("jsp/*");
//		return resolver;
//		하지만, "main"이라는 이름만 가지고는 얘가 어떤 뷰인지 정확히 찾을 수 없다.
//		뷰는 ViewResolver가 찾기 때문에 ViewResolver에게 정확히 뷰를 찾을 수 있도록
//		prefix와 suffix를 붙여주는 작업이다.
//	}

//	thymeleaf 설정은 다음 블로그를 참고: https://eblo.tistory.com/54

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:templates/");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode("LEGACYHTML5");
		templateResolver.setTemplateMode("HTML");
//        templateResolver.setCacheable(isCache);
		return templateResolver;
	}

//	@Bean
//	public LayoutDialect layoutDialect() {
//		return new LayoutDialect();
//	}

	@Bean
	public SpringTemplateEngine templateEngine(MessageSource messageSource) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setTemplateEngineMessageSource(messageSource);
//		templateEngine.addDialect(layoutDialect());
		return templateEngine;
	}

	@Bean
	@Autowired
	public ViewResolver viewResolver(MessageSource messageSource) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine(messageSource));
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(0);
		return viewResolver;
	}

}