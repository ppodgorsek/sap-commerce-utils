package com.github.ppodgorsek.configur.model;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.core.model.ClusterNodeVariation;

/**
 * Wrapper around the SAP Commerce model to make sure the ConfiguR interfaces are used.
 *
 * @author Paul Podgorsek
 */
public class ConfigurClusterNodeVariationModelWrapper implements ClusterNodeVariation {

	private ConfigurClusterNodeVariationModel model;

	/**
	 * Default constructor.
	 *
	 * @param model
	 *              The model.
	 */
	public ConfigurClusterNodeVariationModelWrapper(final ConfigurClusterNodeVariationModel model) {

		super();

		Assert.notNull(model, "The model is required");

		this.model = model;
	}

	@Override
	public String getNodeId() {
		return model.getNodeId();
	}

	@Override
	public String getValue() {
		return model.getValue();
	}

	@Override
	public void setNodeId(final String nodeId) {
		model.setNodeId(nodeId);
	}

	@Override
	public void setValue(final String value) {
		model.setValue(value);
	}

	public ConfigurClusterNodeVariationModel getModel() {
		return model;
	}

}
