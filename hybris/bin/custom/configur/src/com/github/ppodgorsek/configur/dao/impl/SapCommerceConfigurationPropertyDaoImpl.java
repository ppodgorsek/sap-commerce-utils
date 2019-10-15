package com.github.ppodgorsek.configur.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationPropertyDao;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModel;

/**
 * Default implementation of {@link SapCommerceConfigurationPropertyDao}.
 *
 * @author Paul Podgorsek
 */
public class SapCommerceConfigurationPropertyDaoImpl extends DefaultGenericDao<ConfigurPropertyModel>
		implements SapCommerceConfigurationPropertyDao {

	private ModelService modelService;

	/**
	 * Default constructor.
	 *
	 * @param modelService
	 *                     The model service.
	 */
	public SapCommerceConfigurationPropertyDaoImpl(final ModelService modelService) {

		super(ConfigurPropertyModel._TYPECODE);

		Assert.notNull(modelService, "The model service is required");

		this.modelService = modelService;
	}

	@Override
	public void deleteByKey(final String key) {

		ConfigurPropertyModel property = findByKey(key);

		if (property != null) {
			modelService.remove(property);
		}
	}

	@Override
	public List<ConfigurPropertyModel> findByCategoryOrderByKey(final ConfigurCategoryModel category) {

		SortParameters sort = SortParameters.singletonAscending(ConfigurPropertyModel.KEY);
		Map<String, Object> params = Collections.singletonMap(ConfigurPropertyModel.CATEGORY, category);

		List<ConfigurPropertyModel> results = find(params, sort);

		if (results == null) {
			return new ArrayList<>();
		}

		return results;
	}

	@Override
	public ConfigurPropertyModel findByKey(final String key) {

		Map<String, Object> params = Collections.singletonMap(ConfigurPropertyModel.KEY, key);
		List<ConfigurPropertyModel> results = find(params);

		if (results != null) {
			return results.get(0);
		}

		return null;
	}

}
