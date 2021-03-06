package com.azerion.sso;

import com.azerion.sso.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@Slf4j
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties"),
		@PropertySource("classpath:facebook.properties")
})
@EnableCaching(proxyTargetClass = true)
@EnableConfigurationProperties(AppProperties.class)
public class AzerionSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzerionSsoApplication.class, args);
	}

}
