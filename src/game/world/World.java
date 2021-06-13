package game.world;

import game.Globals;
import game.objectSupers.Build;
import game.objectSupers.Entity;
import game.objectSupers.Tile;

public class World {

	public Chunk chunks[];

	public int width, height;

	public World(int width, int height) {
		this.width = width;
		this.height = height;

		chunks = new Chunk[width * height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				chunks[i + j * width] = new Chunk(i * Globals.REAL_CHUNK_SIZE, j * Globals.REAL_CHUNK_SIZE, this);
			}
		}
	}

	public Chunk getChunk(Position pos) {
		return chunks[pos.chunkX + pos.chunkY * this.width];
	}

	public Chunk getChunk(int x, int y) {
		return chunks[x + y * this.width];
	}

	public Tile getTile(Position pos) {
		return chunks[pos.chunkX + pos.chunkY * Globals.X_CHUNK_COUNT].tiles[pos.tileX
				+ pos.tileY * Globals.CHUNK_SIZE];
	}

	public Tile getTile(int x, int y) {
		Position pos = new Position(x, y);
		return chunks[pos.chunkX + pos.chunkY * Globals.X_CHUNK_COUNT].tiles[pos.tileX
				+ pos.tileY * Globals.CHUNK_SIZE];
	}

	public void tick() {

		for (Chunk chunk : chunks) {
			Entity[] entitys = chunk.entitys.toArray(new Entity[chunk.entitys.size()]);
			for (Entity e : entitys) {
				e.onTick(this);
			}
			for (Tile tile : chunk.tiles) {
				tile.onTick(this);
				Build build = tile.build;
				while (build != null) {
					build.onTick(this);
					build = tile.build;
				}
			}
		}

	}

	public Entity addEntity(Entity e, int x, int y) {
		e.setPosition(new Position(x, y, e));
		return e;
	}

	public Entity addEntity(Entity e, Position pos) {
		e.setPosition(new Position(pos.realX, pos.realY, e));
		return e;
	}

	public void removeEntity(Entity e) {
		this.chunks[e.getPosition().chunkX + e.getPosition().chunkY * this.width].entitys.remove(e);
	}

}
