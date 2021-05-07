package game.world;

import game.Globals;

public class World {

	public Chunk chunks[];

	public int width, height;

	public World(int width, int height) {
		this.width = width;
		this.height = height;

		chunks = new Chunk[width * height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				chunks[i + j * width] = new Chunk(i * Globals.TILE_SIZE * Globals.CHUNK_SIZE,
						j * Globals.TILE_SIZE * Globals.CHUNK_SIZE, this);
			}
		}
	}

	public Chunk getChunk(Position pos) {
		return chunks[pos.chunkX + pos.chunkY * this.width];
	}

	public Chunk getChunk(int x, int y) {
		return chunks[x + y * this.width];
	}

}
