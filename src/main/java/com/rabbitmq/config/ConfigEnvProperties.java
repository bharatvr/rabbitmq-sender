package com.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author bharat
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ConfigEnvProperties {

	private String name;

}
