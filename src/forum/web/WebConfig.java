package forum.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * webMVC配置
 * 
 */
@Configuration
/*
 * 将@EnableWebMvc添加给@Configuration类来导入SpringMvc的配置；3.自定义MVC配置，
 * 实现接口WebMvcConfigurer或更可能继承WebMvcConfigurerAdapter,并且使用@EnableWebMvc;
 * 
 */
@EnableWebMvc
/*
 * 定义扫描的路径从中找出标识了需要装配的类自动装配到spring的bean容器中
 * 
 */
@ComponentScan("forum.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 自定义视图解析器
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * 注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 定义静态资源处理
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		/**
		registry.addInterceptor(new SpittlerInterceptor())
				.addPathPatterns(new String[] { "/spittles", "/spittles/**", "/spitter", "/spitter/**" })// 添加拦截
				.excludePathPatterns("/spitter/register");// excludePathPatterns 排除拦截

		registry.addInterceptor(new ManagerInterceptor())
				.addPathPatterns(new String[] { "/manager/managers", "/manager/spitters", "/manager/spittles" })// 添加拦截
				.excludePathPatterns("/manager/register");// excludePathPatterns 排除拦截
		*/

		super.addInterceptors(registry);
	}

}