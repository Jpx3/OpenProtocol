package openprotocol.packet.clientbound;

import openprotocol.Feature;
import openprotocol.packet.Direction;
import openprotocol.packet.Packet;
import openprotocol.packet.PacketRepository;

import java.util.*;

public class HelloPacket extends Packet {
	private Set<Feature> supportedFeatures = new HashSet<>();
	private Map<String, Integer> clientboundPacketIds = new HashMap<>();
	private Map<String, Integer> serverboundPacketIds = new HashMap<>();

	public HelloPacket() {
		super(Direction.CLIENTBOUND, "HELLO");
	}

	public HelloPacket(Set<Feature> supportedFeatures, Map<String, Integer> clientboundPacketIds, Map<String, Integer> serverboundPacketIds) {
		this();
		this.supportedFeatures = supportedFeatures;
		this.clientboundPacketIds = clientboundPacketIds;
		this.serverboundPacketIds = serverboundPacketIds;
		verifyFeatureCompliance();
	}

	public void verifyFeatureCompliance() {
		for (Feature supportedFeature : supportedFeatures) {
			for (Class<? extends Packet> requiredPacket : supportedFeature.requiredPackets()) {
				Direction direction = PacketRepository.directionOf(requiredPacket);
				String packetName = PacketRepository.nameOf(direction, requiredPacket);
				Map<String, Integer> supportedPackets = direction == Direction.CLIENTBOUND ? clientboundPacketIds : serverboundPacketIds;
				if (!supportedPackets.containsKey(packetName)) {
					throw new IllegalArgumentException("Missing packet " + packetName + " for supposed feature " + supportedFeature);
				}
			}
		}
	}

	public Set<Feature> supportedFeatures() {
		return supportedFeatures;
	}

	public Map<String, Integer> clientboundPacketIds() {
		return clientboundPacketIds;
	}

	public Map<String, Integer> serverboundPacketIds() {
		return serverboundPacketIds;
	}
}
