package com.github.ppodgorsek.configur.strategy.impl;

import de.hybris.platform.servicelayer.cluster.ClusterService;

import org.springframework.util.Assert;

import com.github.ppodgorsek.configur.core.strategy.ClusterNodeDeterminationStrategy;

/**
 * Strategy which fetches the current node ID within an SAP Commerce cluster.
 *
 * @author Paul Podgorsek
 */
public class SapCommerceClusterNodeDeterminationStrategy implements ClusterNodeDeterminationStrategy {

	private ClusterService clusterService;

	/**
	 * Default constructor.
	 *
	 * @param clusterService
	 *                       The cluster service.
	 */
	public SapCommerceClusterNodeDeterminationStrategy(final ClusterService clusterService) {

		super();

		Assert.notNull(clusterService, "The cluster service is required");

		this.clusterService = clusterService;
	}

	@Override
	public String getNodeId() {
		return String.valueOf(clusterService.getClusterId());
	}

}
