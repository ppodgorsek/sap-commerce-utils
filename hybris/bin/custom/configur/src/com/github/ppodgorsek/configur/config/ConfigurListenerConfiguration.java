package com.github.ppodgorsek.configur.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ppodgorsek.configur.core.listener.ConfigurationPropertySourceApplicationListener;

/**
 * Listener context configuration for the ConfiguR extension.
 *
 * @author Paul Podgorsek
 */
@Configuration
public class ConfigurListenerConfiguration {

	@Bean
	public ConfigurationPropertySourceApplicationListener configurationPropertySourceApplicationListener(
			final ApplicationContext applicationContext) {
		return new ConfigurationPropertySourceApplicationListener(applicationContext,
				ConfigurConfiguration.CONFIGUR_CONFIGURATION_SERVICE_BEAN_NAME);
	}

}
