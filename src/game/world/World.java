package game.world;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.Tile;

public class World {

	public static Chunk chunks[];

	public static int width, height;

	public static void init(int width, int height) {
		World.width = width;
		World.height = height;

		chunks = new Chunk[width * height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				chunks[i + j * width] = new Chunk(i * Globals.REAL_CHUNK_SIZE, j * Globals.REAL_CHUNK_SIZE);
			}
		}
	}

	public static Chunk getChunk(Position pos) {
		return chunks[pos.chunkX + pos.chunkY * width];
	}

	public static Chunk getChunk(int x, int y) {
		return chunks[x + y * width];
	}

	public static Tile getTile(Position pos) {
		return chunks[pos.chunkX + pos.chunkY * Globals.X_CHUNK_COUNT].tiles[pos.tileX
				+ pos.tileY * Globals.CHUNK_SIZE];
	}

	public static Tile getTile(int x, int y) {
		Position pos = new Position(x, y);
		return chunks[pos.chunkX + pos.chunkY * Globals.X_CHUNK_COUNT].tiles[pos.tileX
				+ pos.tileY * Globals.CHUNK_SIZE];
	}

	public static void tick() {

		for (Chunk chunk : chunks) {
			Entity[] entitys = chunk.entitys.toArray(new Entity[chunk.entitys.size()]);
			for (Entity e : entitys) {
				e.onTick();
			}
			for (Tile tile : chunk.tiles) {
				tile.onTick();
				if (tile.hasBuild())
					tile.getBuild().onTick();
			}
		}

	}

	public static Entity addEntity(Entity e, int x, int y) {
		e.setPosition(new Position(x, y, e));
		return e;
	}

	public static Entity addEntity(Entity e, Position pos) {
		e.setPosition(new Position(pos.realX, pos.realY, e));
		return e;
	}

	public static void removeEntity(Entity e) {
		chunks[e.getPosition().chunkX + e.getPosition().chunkY * width].entitys.remove(e);
	}

}
