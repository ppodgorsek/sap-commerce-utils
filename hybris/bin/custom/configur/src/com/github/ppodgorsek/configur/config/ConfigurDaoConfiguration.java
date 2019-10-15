package com.github.ppodgorsek.configur.config;

import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationCategoryDao;
import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationPropertyDao;
import com.github.ppodgorsek.configur.dao.impl.SapCommerceConfigurationCategoryDaoImpl;
import com.github.ppodgorsek.configur.dao.impl.SapCommerceConfigurationPropertyDaoImpl;

/**
 * DAO context configuration for the ConfiguR extension.
 *
 * @author Paul Podgorsek
 */
@Configuration
public class ConfigurDaoConfiguration {

	@Resource
	private ModelService modelService;

	@Bean
	public SapCommerceConfigurationCategoryDao sapCommerceConfigurationCategoryDao() {
		return new SapCommerceConfigurationCategoryDaoImpl();
	}

	@Bean
	public SapCommerceConfigurationPropertyDao sapCommerceConfigurationPropertyDao() {
		return new SapCommerceConfigurationPropertyDaoImpl(modelService);
	}

}
