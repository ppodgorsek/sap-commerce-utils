package com.github.ppodgorsek.configur.config;

import de.hybris.platform.servicelayer.interceptor.impl.InterceptorMapping;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ppodgorsek.configur.interceptor.ConfigurCategoryInterceptor;
import com.github.ppodgorsek.configur.interceptor.ConfigurPropertyInterceptor;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModel;
import com.github.ppodgorsek.configur.service.impl.SapCommerceConfigurationService;

/**
 * Interceptor context configuration for the ConfiguR extension.
 *
 * @author Paul Podgorsek
 */
@Configuration
public class ConfigurInterceptorConfiguration {

	@Resource
	private SapCommerceConfigurationService configurConfigurationService;

	@Bean
	public ConfigurCategoryInterceptor configurCategoryInterceptor() {
		return new ConfigurCategoryInterceptor(configurConfigurationService);
	}

	@Bean
	public InterceptorMapping configurCategoryInterceptorMapping() {

		InterceptorMapping mapping = new InterceptorMapping();
		mapping.setInterceptor(configurCategoryInterceptor());
		mapping.setTypeCode(ConfigurCategoryModel._TYPECODE);

		return mapping;
	}

	@Bean
	public ConfigurPropertyInterceptor configurPropertyInterceptor() {
		return new ConfigurPropertyInterceptor(configurConfigurationService);
	}

	@Bean
	public InterceptorMapping configurPropertyInterceptorMapping() {

		InterceptorMapping mapping = new InterceptorMapping();
		mapping.setInterceptor(configurPropertyInterceptor());
		mapping.setTypeCode(ConfigurPropertyModel._TYPECODE);

		return mapping;
	}

}
