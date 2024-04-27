# OpenProtocol (proposal)

OpenProtocol is an addition for the Minecraft server-client protocol,
adding a rich feature set, making cheating more difficult and allowing recent additions to the protocol to be backwards compatible.
Any client and any server is invited to implement this standard, bringing new life into the game.

### Features

- Display custom server logo above tab screen
- Text input prompt
- Server switch packet
- Support for 1.20.4+ cookie packets
- Chat-encryption backwards compatibility
- Set cps constraint policy
- Set block-placement policy (fixes ghost blocks) 
- Chunk & Lightning caching (Planned)
- Change block properties (Planned)
- Allow servers to modify magic movement values
- Per-frame mouse/click information
- More movement-related information
- Server-issued movement & rotations
- Packet acknowledgements

& much more

#### Feature requirements

Each feature can individually be supported or not.
Your client might just support one feature and call it a day.

### Benefits

#### For clients

Significantly lower network usage (chunk caching), 
more ingame features, fewer incorrect detections by anticheats.

#### For servers
Less performance required by simulation-based anticheats + fewer false positives,
more customizations for players, fewer cheaters and significantly less bandwidth usage.


### Implementation


#### For clients
Forge mod?

#### For servers

We offer a bukkit plugin `ServerOpenProtocol` (github) to communicate with your players.
It offers both a ProtocolLib and PacketEvents implementation.
