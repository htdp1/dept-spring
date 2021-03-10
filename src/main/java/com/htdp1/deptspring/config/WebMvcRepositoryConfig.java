package com.htdp1.deptspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.htdp1.deptspring.interceptor.DBEditorTenantInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebMvcRepositoryConfig implements WebMvcConfigurer {

	private @Value("${session.enabled}") boolean enabled;

	@Autowired
	private DBEditorTenantInterceptor dbEditorTenantInterceptor;

	@Bean
	public MappedInterceptor dbEditorTenantInterceptor() {
		log.debug("enabled => " + enabled);

		if (enabled) {
			return new MappedInterceptor(new String[] { "/api/**" }, dbEditorTenantInterceptor);
		}

		return null;
	}
}