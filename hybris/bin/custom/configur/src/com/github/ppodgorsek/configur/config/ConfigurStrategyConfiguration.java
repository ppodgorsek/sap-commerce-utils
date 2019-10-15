package com.github.ppodgorsek.configur.config;

import de.hybris.platform.servicelayer.cluster.ClusterService;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ppodgorsek.configur.core.strategy.ClusterNodeDeterminationStrategy;
import com.github.ppodgorsek.configur.strategy.impl.SapCommerceClusterNodeDeterminationStrategy;

/**
 * Strategy context configuration for the ConfiguR extension.
 *
 * @author Paul Podgorsek
 */
@Configuration
public class ConfigurStrategyConfiguration {

	@Resource
	private ClusterService clusterService;

	@Bean
	public ClusterNodeDeterminationStrategy sapCommerceClusterNodeDeterminationStrategy() {
		return new SapCommerceClusterNodeDeterminationStrategy(clusterService);
	}

}
