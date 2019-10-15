package com.github.ppodgorsek.configur.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import com.github.ppodgorsek.configur.cache.service.CachedConfigurationService;
import com.github.ppodgorsek.configur.core.service.ConfigurationService;
import com.github.ppodgorsek.configur.core.strategy.ClusterNodeDeterminationStrategy;
import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationCategoryDao;
import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationPropertyDao;
import com.github.ppodgorsek.configur.service.impl.SapCommerceConfigurationService;

/**
 * Service context configuration for the ConfiguR extension.
 *
 * @author Paul Podgorsek
 */
@Configuration
public class ConfigurServiceConfiguration {

	@Resource
	private ClusterNodeDeterminationStrategy sapCommerceClusterNodeDeterminationStrategy;

	@Resource
	private SapCommerceConfigurationCategoryDao sapCommerceConfigurationCategoryDao;

	@Resource
	private SapCommerceConfigurationPropertyDao sapCommerceConfigurationPropertyDao;

	@Bean
	@AliasFor(ConfigurConfiguration.CONFIGUR_CONFIGURATION_SERVICE_BEAN_NAME)
	public ConfigurationService cachedConfigurationService() {
		return new CachedConfigurationService(sapCommerceConfigurationService());
	}

	@Bean
	public ConfigurationService sapCommerceConfigurationService() {
		return new SapCommerceConfigurationService(sapCommerceClusterNodeDeterminationStrategy,
				sapCommerceConfigurationCategoryDao, sapCommerceConfigurationPropertyDao);
	}

}
