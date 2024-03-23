package openprotocol.packet;

import openprotocol.packet.clientbound.HelloPacket;
import openprotocol.packet.clientbound.RequestServerSwitchPacket;
import openprotocol.packet.serverbound.HelloResponsePacket;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PacketRepository {
	private static final Map<Direction, Map<String, Class<? extends Packet>>> nameToPacketClass = new EnumMap<>(Direction.class);
	private static final Map<Direction, Map<Class<? extends Packet>, String>> packetClassToName = new EnumMap<>(Direction.class);

	static {
		registerClientbound(HelloPacket.class);
		registerClientbound(RequestServerSwitchPacket.class);


		registerServerbound(HelloResponsePacket.class);

	}

	private static void registerClientbound(Class<? extends Packet> packetClass) {
		register(Direction.CLIENTBOUND, packetClass);
	}

	private static void registerServerbound(Class<? extends Packet> packetClass) {
		register(Direction.SERVERBOUND, packetClass);
	}

	private static void register(Direction direction, Class<? extends Packet> packetClass) {
		Packet sample = supplierOf(packetClass).get();
		String packetName = sample.packetName();
		Direction packetDirection = sample.packetDirection();
		if (!packetName.equals(packetName.toUpperCase())) {
			throw new IllegalArgumentException("Name must be uppercase");
		}
		if (packetName.length() > 255) {
			throw new IllegalArgumentException("Name must be less than 255 characters");
		}
		if (direction != packetDirection) {
			throw new IllegalArgumentException("Direction must match");
		}
		nameToPacketClass.computeIfAbsent(direction, x -> new HashMap<>())
			.put(packetName, packetClass);
		packetClassToName.computeIfAbsent(direction, x -> new HashMap<>())
			.put(packetClass, packetName);
	}

	public static String nameOf(Direction direction, Class<? extends Packet> packetClass) {
		return packetClassToName.get(direction).get(packetClass);
	}

	public static Class<? extends Packet> classOf(Direction direction, String packetName) {
		return nameToPacketClass.get(direction).get(packetName);
	}

	public static Direction directionOf(Class<? extends Packet> packetClass) {
		return supplierOf(packetClass).get().packetDirection();
	}

	private static <P extends Packet> Supplier<P> supplierOf(Class<P> packetClass) {
		return () -> {
			try {
				return packetClass.newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		};
	}
}
