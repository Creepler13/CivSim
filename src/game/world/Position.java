package game.world;

import game.Globals;
import game.Main;
import game.objectSupers.Entity;

public class Position {

	public int tileX, tileY;
	public int chunkX, chunkY;
	public int realX, realY;

	private Entity entity;

	public Position(int x, int y) {
		setPosition(x, y);
	}

	public Position(int x, int y, Entity entity) {
		this.entity = entity;

		this.realX = (x >= 0) ? (x <= Globals.REAL_MAP_WIDTH) ? x : Globals.REAL_MAP_WIDTH : 0;
		this.realY = (y >= 0) ? (y <= Globals.REAL_MAP_HEIGHT) ? y : Globals.REAL_MAP_HEIGHT : 0;
		this.chunkX = this.realX / Globals.TILE_SIZE / Globals.CHUNK_SIZE;
		this.chunkY = this.chunkY / Globals.TILE_SIZE / Globals.CHUNK_SIZE;

		Main.world.getChunk(this.chunkX, this.chunkY).entitys.add(entity);
		setPosition(this.realX, this.realY);
	}

	public void setPosition(int x, int y) {
		this.realX = (x >= 0) ? (x <= Globals.REAL_MAP_WIDTH) ? x : Globals.REAL_MAP_WIDTH : 0;
		this.realY = (y >= 0) ? (y <= Globals.REAL_MAP_HEIGHT) ? y : Globals.REAL_MAP_HEIGHT : 0;
		int newChunkX = x / Globals.TILE_SIZE / Globals.CHUNK_SIZE;
		int newChunkY = y / Globals.TILE_SIZE / Globals.CHUNK_SIZE;

		this.tileX = (x - newChunkX * Globals.REAL_CHUNK_SIZE) / Globals.TILE_SIZE;
		this.tileY = (y - newChunkY * Globals.REAL_CHUNK_SIZE) / Globals.TILE_SIZE;

		if (this.entity != null && (this.chunkX != newChunkX || this.chunkY != newChunkY)) {
			Main.world.getChunk(Position.fromChunkPos(this.chunkX, this.chunkY)).entitys.remove(this.entity);
			Main.world.getChunk(Position.fromChunkPos(newChunkX, newChunkY)).entitys.add(this.entity);
		}
		this.chunkX = newChunkX;
		this.chunkY = newChunkY;

	};

	public boolean isEntity() {
		return this.entity != null;
	}

	@Override
	public String toString() {
		return "[realX:" + this.realX + ", realY:" + this.realY + ", chunkX:" + this.chunkX + ", chunkY:" + this.chunkY
				+ ", tileX:" + this.tileX + ", tileY:" + this.tileY + ", Entity:" + this.entity + "]";
	}

	public static Position fromChunkPos(int x, int y) {
		return new Position(x * Globals.REAL_CHUNK_SIZE, y * Globals.REAL_CHUNK_SIZE);
	}

}
