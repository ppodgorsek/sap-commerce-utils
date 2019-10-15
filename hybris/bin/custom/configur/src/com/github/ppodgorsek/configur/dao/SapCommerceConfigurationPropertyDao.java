package com.github.ppodgorsek.configur.dao;

import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;

import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModel;

/**
 * DAO for {@link ConfigurPropertyModel}s.
 *
 * @author Paul Podgorsek
 */
public interface SapCommerceConfigurationPropertyDao extends GenericDao<ConfigurPropertyModel> {

	/**
	 * Deletes the entity with the given key.
	 *
	 * @param key
	 *            The property key, must not be {@code null}.
	 */
	void deleteByKey(String key);

	/**
	 * Fetches the child properties of a given category.
	 *
	 * @param category
	 *                 The parent category.
	 * @return The list of child properties of the provided category, or an empty list if there are none.
	 */
	List<ConfigurPropertyModel> findByCategoryOrderByKey(ConfigurCategoryModel category);

	/**
	 * Fetches a property by its key.
	 *
	 * @param key
	 *            The property key.
	 * @return The property which has the provided key, or {@code null} if there isn't one.
	 */
	ConfigurPropertyModel findByKey(String key);

}
