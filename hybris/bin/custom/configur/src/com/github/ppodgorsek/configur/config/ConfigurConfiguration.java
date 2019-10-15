package com.github.ppodgorsek.configur.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.ppodgorsek.configur.cache.config.CacheConfiguration;

/**
 * Context configuration for the ConfiguR extension.
 *
 * @author Paul Podgorsek
 */
@Configuration
@Import({ CacheConfiguration.class, ConfigurDaoConfiguration.class, ConfigurInterceptorConfiguration.class,
		ConfigurListenerConfiguration.class, ConfigurServiceConfiguration.class, ConfigurStrategyConfiguration.class })
@EnableCaching
public class ConfigurConfiguration {

	public static final String CONFIGUR_CONFIGURATION_SERVICE_BEAN_NAME = "configurConfigurationService";

}
