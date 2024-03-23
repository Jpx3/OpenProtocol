package openprotocol.packet.clientbound;

import openprotocol.packet.PacketAttribute;
import openprotocol.packet.Packet;

public class ServerSwitchPacket extends Packet {
	@PacketAttribute(name = "domain")
	private String domain;
	@PacketAttribute(name = "port")
	private int port;

	public ServerSwitchPacket() {
		super("SERVER_SWITCH");
	}

	public ServerSwitchPacket(String domain, int port) {
		this();
		this.domain = domain;
		this.port = port;
	}

	public String domain() {
		return domain;
	}

	public int port() {
		return port;
	}
}
