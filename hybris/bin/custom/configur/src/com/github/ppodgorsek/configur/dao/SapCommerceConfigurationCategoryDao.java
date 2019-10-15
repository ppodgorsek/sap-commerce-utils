package com.github.ppodgorsek.configur.dao;

import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;

import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;

/**
 * DAO for {@link ConfigurCategoryModel}s.
 * 
 * @author Paul Podgorsek
 */
public interface SapCommerceConfigurationCategoryDao extends GenericDao<ConfigurCategoryModel> {

	/**
	 * Fetches a configuration category by its key.
	 *
	 * @param key
	 *            The category key.
	 * @return The category having the provided key, or {@code null} if there isn't one.
	 */
	ConfigurCategoryModel findByKey(String key);

	/**
	 * Fetches all categories which have a given parent, ordered by key.
	 *
	 * @param category
	 *                 The parent category.
	 * @return The categories having the provided parent, or an empty list if there are none.
	 */
	List<ConfigurCategoryModel> findByParentOrderByKey(ConfigurCategoryModel category);

}
