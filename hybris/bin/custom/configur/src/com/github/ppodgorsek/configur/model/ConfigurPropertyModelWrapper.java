package com.github.ppodgorsek.configur.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.core.model.ClusterNodeVariation;
import com.github.ppodgorsek.configur.core.model.ConfigurationCategory;
import com.github.ppodgorsek.configur.core.model.ConfigurationProperty;
import com.github.ppodgorsek.configur.util.ConfigurationModelConversionUtils;

/**
 * Wrapper around the SAP Commerce model to make sure the ConfiguR interfaces are used.
 *
 * @author Paul Podgorsek
 */
public class ConfigurPropertyModelWrapper implements ConfigurationProperty {

	private ConfigurPropertyModel model;

	/**
	 * Default constructor.
	 *
	 * @param model
	 *              The model.
	 */
	public ConfigurPropertyModelWrapper(final ConfigurPropertyModel model) {

		super();

		Assert.notNull(model, "The model is required");

		this.model = model;
	}

	@Override
	public ConfigurationCategory getCategory() {
		return new ConfigurCategoryModelWrapper(model.getCategory());
	}

	@Override
	public Set<ClusterNodeVariation> getClusterNodeVariations() {

		Set<ClusterNodeVariation> variations = new HashSet<>();

		for (ConfigurClusterNodeVariationModel variationModel : model.getClusterNodeVariations()) {
			variations.add(new ConfigurClusterNodeVariationModelWrapper(variationModel));
		}

		return variations;
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
	public String getValue() {
		return model.getValue();
	}

	@Override
	public void setCategory(final ConfigurationCategory category) {
		model.setCategory(ConfigurationModelConversionUtils.convertToModel(category));
	}

	@Override
	public void setClusterNodeVariations(final Set<ClusterNodeVariation> clusterNodeVariations) {
		model.setClusterNodeVariations(ConfigurationModelConversionUtils.convertToModel(clusterNodeVariations));
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
	public void setValue(final String value) {
		model.setValue(value);
	}

	public ConfigurPropertyModel getModel() {
		return model;
	}

}
