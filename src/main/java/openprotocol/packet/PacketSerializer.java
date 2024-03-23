package openprotocol.packet;

import com.google.gson.Gson;

import java.io.*;
import java.nio.ByteBuffer;

public class PacketSerializer {
	private final static ThreadLocal<Gson> gson = ThreadLocal.withInitial(Gson::new);

	private static Gson gson() {
		return gson.get();
	}

	public static void serialize(OutputStream stream, Packet packet) throws IOException {
		Writer writer = new OutputStreamWriter(stream);
		gson().toJson(packet, writer);
		writer.flush();
	}

	public static Packet deserialize(InputStream stream) {
		Reader reader = new InputStreamReader(stream);
		return gson().fromJson(reader, Packet.class);
	}

	public static ByteBuffer serializeToBuffer(Packet packet) {
		return ByteBuffer.wrap(gson().toJson(packet).getBytes());
	}

	public static Packet deserializeFromBuffer(ByteBuffer buffer) {
		return gson().fromJson(new String(buffer.array()), Packet.class);
	}
}
