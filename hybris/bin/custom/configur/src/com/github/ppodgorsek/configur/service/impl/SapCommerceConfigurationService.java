package com.github.ppodgorsek.configur.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.core.model.ConfigurationCategory;
import com.github.ppodgorsek.configur.core.model.ConfigurationProperty;
import com.github.ppodgorsek.configur.core.service.impl.AbstractConfigurationService;
import com.github.ppodgorsek.configur.core.strategy.ClusterNodeDeterminationStrategy;
import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationCategoryDao;
import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationPropertyDao;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModelWrapper;

/**
 * SAP Commerce implementation of the {@link com.github.ppodgorsek.configur.core.service.ConfigurationService
 * ConfigurationService} interface.
 *
 * @author Paul Podgorsek
 */
public class SapCommerceConfigurationService extends AbstractConfigurationService {

	private SapCommerceConfigurationCategoryDao configurationCategoryDao;

	private SapCommerceConfigurationPropertyDao configurationPropertyDao;

	/**
	 * Default constructor.
	 *
	 * @param clusterNodeDeterminationStrategy
	 *                                         The cluster node determination strategy.
	 * @param configurationCategoryDao
	 *                                         The configuration category DAO.
	 * @param configurationPropertyDao
	 *                                         The configuration property DAO.
	 */
	public SapCommerceConfigurationService(final ClusterNodeDeterminationStrategy clusterNodeDeterminationStrategy,
			final SapCommerceConfigurationCategoryDao configurationCategoryDao,
			final SapCommerceConfigurationPropertyDao configurationPropertyDao) {

		super(clusterNodeDeterminationStrategy);

		Assert.notNull(configurationCategoryDao, "The configuration category DAO is required");
		Assert.notNull(configurationPropertyDao, "The configuration property DAO is required");

		this.configurationCategoryDao = configurationCategoryDao;
		this.configurationPropertyDao = configurationPropertyDao;
	}

	@Override
	public void deleteCategory(final String categoryKey) {

		// This method will only be called by interceptors to make sure the cache is properly handled.
		// The processing will be done in a standard way by the model service.
	}

	@Override
	public void deleteProperty(final String propertyKey) {

		// This method will only be called by interceptors to make sure the cache is properly handled.
		// The processing will be done in a standard way by the model service.
	}

	@Override
	public List<ConfigurationProperty> getByCategory(final String categoryKey) {

		ConfigurCategoryModel category = configurationCategoryDao.findByKey(categoryKey);

		if (category == null) {
			return Collections.emptyList();
		}
		else {
			List<ConfigurationProperty> properties = new ArrayList<>();

			for (ConfigurPropertyModel property : configurationPropertyDao.findByCategoryOrderByKey(category)) {
				properties.add(new ConfigurPropertyModelWrapper(property));
			}

			return properties;
		}
	}

	@Override
	public ConfigurationProperty getProperty(final String propertyKey) {
		return new ConfigurPropertyModelWrapper(configurationPropertyDao.findByKey(propertyKey));
	}

	@Override
	public ConfigurationCategory save(final ConfigurationCategory category) {

		// This method will only be called by interceptors to make sure the cache is properly handled.
		// The processing will be done in a standard way by the model service.

		return category;
	}

	@Override
	public ConfigurationProperty save(final ConfigurationProperty property) {

		// This method will only be called by interceptors to make sure the cache is properly handled.
		// The processing will be done in a standard way by the model service.

		return property;
	}

}
