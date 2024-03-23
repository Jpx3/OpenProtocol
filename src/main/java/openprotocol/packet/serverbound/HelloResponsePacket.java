package openprotocol.packet.serverbound;

import openprotocol.Feature;
import openprotocol.packet.Direction;
import openprotocol.packet.Packet;
import openprotocol.packet.PacketRepository;

import java.util.*;

public class HelloResponsePacket extends Packet {
	private Set<Feature> featureSubset = new HashSet<>();
	private Map<String, Integer> clientboundPacketIds = new HashMap<>();
	private Map<String, Integer> serverboundPacketIds = new HashMap<>();

	public HelloResponsePacket() {
		super(Direction.SERVERBOUND, "HELLO_RESPONSE");
	}

	public HelloResponsePacket(Set<Feature> featureSubset, Map<String, Integer> clientboundPacketIds, Map<String, Integer> serverboundPacketIds) {
		this();
		this.featureSubset = featureSubset;
		this.clientboundPacketIds = clientboundPacketIds;
		this.serverboundPacketIds = serverboundPacketIds;
		verifyFeatureCompliance();
	}

	public void verifyFeatureCompliance() {
		for (Feature supportedFeature : featureSubset) {
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

	public Set<Feature> featureSubset() {
		return Collections.unmodifiableSet(featureSubset);
	}

	public Map<String, Integer> clientboundPacketIds() {
		return Collections.unmodifiableMap(clientboundPacketIds);
	}

	public Map<String, Integer> serverboundPacketIds() {
		return Collections.unmodifiableMap(serverboundPacketIds);
	}
}
