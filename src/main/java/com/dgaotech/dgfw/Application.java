package com.dgaotech.dgfw;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven
								// />，在service中增加@Transactional
@ComponentScan
public class Application extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

	@Value("${server.port}")
	public int port;

	// dataSource这里使用的是Hikari,你也可以使用其他的
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig(getClass().getClassLoader().getResource("db.properties").getPath());
		return new HikariDataSource(config);
	}

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	@Bean
	public StringHttpMessageConverter customStringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		messageConverter.setWriteAcceptCharset(false);
		return messageConverter;
	}
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 增加消息转换器
		converters.add(customJackson2HttpMessageConverter());
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		converters.add(customStringHttpMessageConverter());
		
		super.configureMessageConverters(converters);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(port);
	}

	// @Component
	// public class AppHealthIndicator implements HealthIndicator {
	// @Override
	// public Health health() {
	// return Health.up().build();
	// }
	// }

}
