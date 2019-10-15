package com.github.ppodgorsek.configur.interceptor;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModelWrapper;
import com.github.ppodgorsek.configur.service.impl.SapCommerceConfigurationService;

/**
 * Interceptor triggered upon removal of an item to make sure the cache has been properly handled.
 *
 * @author Paul Podgorsek
 */
public class ConfigurCategoryInterceptor
		implements PrepareInterceptor<ConfigurCategoryModel>, RemoveInterceptor<ConfigurCategoryModel> {

	private SapCommerceConfigurationService sapCommerceConfigurationService;

	/**
	 * Default constructor.
	 *
	 * @param sapCommerceConfigurationService
	 *                                        The configuration service.
	 */
	public ConfigurCategoryInterceptor(final SapCommerceConfigurationService sapCommerceConfigurationService) {

		super();

		Assert.notNull(sapCommerceConfigurationService, "The configuration service is required");

		this.sapCommerceConfigurationService = sapCommerceConfigurationService;
	}

	@Override
	public void onPrepare(final ConfigurCategoryModel category, final InterceptorContext context)
			throws InterceptorException {
		sapCommerceConfigurationService.save(new ConfigurCategoryModelWrapper(category));
	}

	@Override
	public void onRemove(final ConfigurCategoryModel category, final InterceptorContext context)
			throws InterceptorException {
		sapCommerceConfigurationService.deleteCategory(category.getKey());
	}

}
