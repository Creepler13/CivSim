package game.world;

import game.Globals;

public class Position {

	public int tileX, tileY;
	public int chunkX, chunkY;
	public int realX, realY;

	public boolean isFromEntity = false;

	private World world;
	private Entity entity;

	public Position(int x, int y) {
		setPosition(x, y);
	}

	public Position(int x, int y, World world, Entity entity) {
		this.isFromEntity = true;
		this.entity = entity;
		this.world = world;
		setPosition(x, y);
	}

	public static Position fromChunkPos(int x, int y) {
		return new Position(x * Globals.TILE_SIZE * Globals.CHUNK_SIZE, y * Globals.TILE_SIZE * Globals.CHUNK_SIZE);
	}

	public void setPosition(int x, int y) {
		this.realX = x;
		this.realY = y;
		int newChunkX = x / Globals.TILE_SIZE / Globals.CHUNK_SIZE;
		int newChunkY = y / Globals.TILE_SIZE / Globals.CHUNK_SIZE;

		this.tileX = (x - this.chunkX * Globals.TILE_SIZE * Globals.CHUNK_SIZE) / Globals.TILE_SIZE;
		this.tileY = (y - this.chunkY * Globals.TILE_SIZE * Globals.CHUNK_SIZE) / Globals.TILE_SIZE;

		if (this.isFromEntity) {
			this.world.getChunk(Position.fromChunkPos(this.chunkX, this.chunkY)).entitys.remove(this.entity);
			this.world.getChunk(this).entitys.add(this.entity);
		}

		this.chunkX = newChunkX;
		this.chunkY = newChunkY;

	};

	@Override
	public String toString() {
		return "[realX:" + this.realX + ", realY:" + this.realY + ", chunkX:" + this.chunkX + ", chunkY:" + this.chunkY
				+ ", tileX:" + this.tileX + ", tileY:" + this.tileY + ", isFromEntity:" + this.isFromEntity + "]";
	}

}
