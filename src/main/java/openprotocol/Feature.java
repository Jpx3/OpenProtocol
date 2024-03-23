package openprotocol;

import openprotocol.packet.Packet;
import openprotocol.packet.clientbound.RequestServerSwitchPacket;

import java.util.EnumSet;
import java.util.Set;

public enum Feature {
	SERVER_SWITCH(RequestServerSwitchPacket.class)



	;

	public static Set<Feature> all() {
		return EnumSet.allOf(Feature.class);
	}

	public static Set<Feature> none() {
		return EnumSet.noneOf(Feature.class);
	}

	private final Class<? extends Packet>[] requiredPackets;

	Feature(Class<? extends Packet>... requiredPackets) {
		this.requiredPackets = requiredPackets;
	}

	public Class<? extends Packet>[] requiredPackets() {
		return requiredPackets;
	}
}
