package game.world;

import java.util.HashMap;

public class World {

	public HashMap<Position, Chunk> map = new HashMap<>();

	public World() {

	}

	public Chunk getChunk(Position pos) {
		return map.get(pos);
	}

}
