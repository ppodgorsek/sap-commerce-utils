package com.github.ppodgorsek.configur.interceptor;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.model.ConfigurPropertyModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModelWrapper;
import com.github.ppodgorsek.configur.service.impl.SapCommerceConfigurationService;

/**
 * Interceptor triggered upon removal of an item to make sure the cache has been properly handled.
 *
 * @author Paul Podgorsek
 */
public class ConfigurPropertyInterceptor
		implements PrepareInterceptor<ConfigurPropertyModel>, RemoveInterceptor<ConfigurPropertyModel> {

	private SapCommerceConfigurationService sapCommerceConfigurationService;

	/**
	 * Default constructor.
	 *
	 * @param sapCommerceConfigurationService
	 *                                        The configuration service.
	 */
	public ConfigurPropertyInterceptor(final SapCommerceConfigurationService sapCommerceConfigurationService) {

		super();

		Assert.notNull(sapCommerceConfigurationService, "The configuration service is required");

		this.sapCommerceConfigurationService = sapCommerceConfigurationService;
	}

	@Override
	public void onPrepare(final ConfigurPropertyModel property, final InterceptorContext context)
			throws InterceptorException {
		sapCommerceConfigurationService.save(new ConfigurPropertyModelWrapper(property));
	}

	@Override
	public void onRemove(final ConfigurPropertyModel property, final InterceptorContext context)
			throws InterceptorException {
		sapCommerceConfigurationService.deleteProperty(property.getKey());
	}

}
