package openprotocol.packet;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class PacketRepository {
	private static final Map<Direction, Map<String, Class<? extends Packet>>> nameToPacketClass = new EnumMap<>(Direction.class);

	static {




	}

	private static void registerClientbound(Class<? extends Packet> packetClass) {

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
