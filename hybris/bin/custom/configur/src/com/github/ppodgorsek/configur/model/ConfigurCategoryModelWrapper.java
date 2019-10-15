package com.github.ppodgorsek.configur.model;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.core.model.ConfigurationCategory;
import com.github.ppodgorsek.configur.util.ConfigurationModelConversionUtils;

/**
 * Wrapper around the SAP Commerce model to make sure the ConfiguR interfaces are used.
 *
 * @author Paul Podgorsek
 */
public class ConfigurCategoryModelWrapper implements ConfigurationCategory {

	private ConfigurCategoryModel model;

	/**
	 * Default constructor.
	 *
	 * @param category
	 *                 The category to wrap.
	 */
	public ConfigurCategoryModelWrapper(final ConfigurCategoryModel category) {

		super();

		Assert.notNull(category, "The model is required");

		model = category;
	}

	/**
	 * Constructor wrapping any kind of {@link ConfigurationCategory}.
	 *
	 * @param category
	 *                 The category to wrap.
	 */
	public ConfigurCategoryModelWrapper(final ConfigurationCategory category) {

		super();

		Assert.notNull(category, "The model is required");

		model = ConfigurationModelConversionUtils.convertToModel(category);
	}

	@Override
	public String getDescription() {
		return model.getDescription();
	}

	@Override
	public String getKey() {
		return model.getKey();
	}

	@Override
	public String getName() {
		return model.getName();
	}

	@Override
	public ConfigurationCategory getParent() {
		return new ConfigurCategoryModelWrapper(model.getParentCategory());
	}

	@Override
	public void setDescription(final String description) {
		model.setDescription(description);
	}

	@Override
	public void setKey(final String key) {
		model.setKey(key);
	}

	@Override
	public void setName(final String name) {
		model.setName(name);
	}

	@Override
	public void setParent(final ConfigurationCategory category) {
		model.setParentCategory(ConfigurationModelConversionUtils.convertToModel(category));
	}

	public ConfigurCategoryModel getModel() {
		return model;
	}

}
