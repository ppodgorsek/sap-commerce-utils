package com.github.ppodgorsek.configur.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.github.ppodgorsek.configur.dao.SapCommerceConfigurationCategoryDao;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;

/**
 * Default implementation of {@link SapCommerceConfigurationCategoryDao}.
 *
 * @author Paul Podgorsek
 */
public class SapCommerceConfigurationCategoryDaoImpl extends DefaultGenericDao<ConfigurCategoryModel>
		implements SapCommerceConfigurationCategoryDao {

	/**
	 * Default constructor.
	 */
	public SapCommerceConfigurationCategoryDaoImpl() {
		super(ConfigurCategoryModel._TYPECODE);
	}

	@Override
	public ConfigurCategoryModel findByKey(final String key) {

		Map<String, Object> params = Collections.singletonMap(ConfigurCategoryModel.KEY, key);
		List<ConfigurCategoryModel> results = find(params);

		if (results != null) {
			return results.get(0);
		}

		return null;
	}

	@Override
	public List<ConfigurCategoryModel> findByParentOrderByKey(final ConfigurCategoryModel category) {

		SortParameters sort = SortParameters.singletonAscending(ConfigurCategoryModel.KEY);
		Map<String, Object> params = Collections.singletonMap(ConfigurCategoryModel.PARENTCATEGORY, category);

		List<ConfigurCategoryModel> results = find(params, sort);

		if (results == null) {
			return new ArrayList<>();
		}

		return results;
	}

}
