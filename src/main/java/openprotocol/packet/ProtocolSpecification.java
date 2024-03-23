package openprotocol.packet;

import openprotocol.Feature;

import java.util.*;

public class ProtocolSpecification {
	private final Map<Direction, Map<Integer, String>> packetIds = new HashMap<>();
	private final Set<Feature> supportedFeatures = new HashSet<>();

	public ProtocolSpecification(Set<Feature> supportedFeatures, Map<Direction, Map<Integer, String>> packetIds) {
		this.supportedFeatures.addAll(supportedFeatures);
		this.packetIds.putAll(packetIds);
	}

	public Set<Feature> supportedFeatures() {
		return Collections.unmodifiableSet(supportedFeatures);
	}

	public boolean supportsFeature(Feature feature) {
		return supportedFeatures.contains(feature);
	}

	public void removeFeatureIfNotIn(Set<Feature> features) {
		supportedFeatures.retainAll(features);
	}

	public void removePacketIdIfNotIn(Direction direction, Set<Integer> ids) {
		packetIds.get(direction).keySet().retainAll(ids);
	}

}
