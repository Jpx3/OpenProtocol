package openprotocol.packet;

public class Packet {
	private final Direction direction;
	private final String name;

	public Packet(Direction direction, String name) {
		this.direction = direction;
		this.name = name;
	}

	public final String packetName() {
		return name;
	}

	public Direction packetDirection() {
		return direction;
	}
}
