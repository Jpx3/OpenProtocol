package openprotocol.packet;

public class Packet {
	private String name;

	public Packet(String name) {
		this.name = name;
	}

	public final String packetName() {
		return name;
	}
}
