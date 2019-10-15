package com.github.ppodgorsek.configur.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.ppodgorsek.configur.core.model.ClusterNodeVariation;
import com.github.ppodgorsek.configur.core.model.ConfigurationCategory;
import com.github.ppodgorsek.configur.core.model.ConfigurationProperty;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModel;
import com.github.ppodgorsek.configur.model.ConfigurCategoryModelWrapper;
import com.github.ppodgorsek.configur.model.ConfigurClusterNodeVariationModel;
import com.github.ppodgorsek.configur.model.ConfigurClusterNodeVariationModelWrapper;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModel;
import com.github.ppodgorsek.configur.model.ConfigurPropertyModelWrapper;

/**
 * Utility class to convert ConfiguR interfaces to SAP Commerce models.
 *
 * @author Paul Podgorsek
 */
public final class ConfigurationModelConversionUtils {

	/**
	 * Default private constructor to avoid instantiating this class.
	 */
	private ConfigurationModelConversionUtils() {
		super();
	}

	public static ConfigurCategoryModel convertToModel(final ConfigurationCategory category) {

		if (category == null) {
			return null;
		}

		if (category instanceof ConfigurCategoryModel) {
			return (ConfigurCategoryModel) category;
		}

		if (category instanceof ConfigurCategoryModelWrapper) {
			return ((ConfigurCategoryModelWrapper) category).getModel();
		}

		ConfigurCategoryModel model = new ConfigurCategoryModel();
		model.setDescription(category.getDescription());
		model.setKey(category.getKey());
		model.setName(category.getName());
		model.setParentCategory(convertToModel(category.getParent()));

		return model;
	}

	public static ConfigurPropertyModel convertToModel(final ConfigurationProperty property) {

		if (property == null) {
			return null;
		}

		if (property instanceof ConfigurPropertyModel) {
			return (ConfigurPropertyModel) property;
		}

		if (property instanceof ConfigurPropertyModelWrapper) {
			return ((ConfigurPropertyModelWrapper) property).getModel();
		}

		ConfigurPropertyModel model = new ConfigurPropertyModel();
		model.setCategory(convertToModel(property.getCategory()));
		model.setClusterNodeVariations(convertToModel(property.getClusterNodeVariations()));
		model.setDescription(property.getDescription());
		model.setKey(property.getKey());
		model.setName(property.getName());
		model.setValue(property.getValue());

		return model;
	}

	public static List<ConfigurClusterNodeVariationModel> convertToModel(
			final Set<ClusterNodeVariation> clusterNodeVariations) {

		if (clusterNodeVariations == null) {
			return null;
		}

		List<ConfigurClusterNodeVariationModel> variationModels = new ArrayList<>();

		for (ClusterNodeVariation variation : clusterNodeVariations) {
			variationModels.add(convertToModel(variation));
		}

		return variationModels;
	}

	public static ConfigurClusterNodeVariationModel convertToModel(final ClusterNodeVariation clusterNodeVariation) {

		if (clusterNodeVariation == null) {
			return null;
		}

		if (clusterNodeVariation instanceof ConfigurClusterNodeVariationModel) {
			return (ConfigurClusterNodeVariationModel) clusterNodeVariation;
		}

		if (clusterNodeVariation instanceof ConfigurClusterNodeVariationModelWrapper) {
			return ((ConfigurClusterNodeVariationModelWrapper) clusterNodeVariation).getModel();
		}

		return null;
	}

}
