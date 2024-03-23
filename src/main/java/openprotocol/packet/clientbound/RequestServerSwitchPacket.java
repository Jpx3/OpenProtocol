package openprotocol.packet.clientbound;

import openprotocol.packet.Direction;
import openprotocol.packet.Packet;

public class RequestServerSwitchPacket extends Packet {
	private String domain;
	private int port;

	public RequestServerSwitchPacket() {
		super(Direction.CLIENTBOUND, "SERVER_SWITCH");
	}

	public RequestServerSwitchPacket(String domain, int port) {
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
