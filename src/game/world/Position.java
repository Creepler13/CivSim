package game.world;

import game.Globals;

public class Position {

	public int tileX, tileY;
	public int chunkX, chunkY;
	public int realX, realY;

	public Position(int x, int y) {
		setPosition(x, y);
	}

	public void setPosition(int x, int y) {
		this.realX = x;
		this.realY = y;
		this.chunkX = x * Globals.CHUNK_SIZE;
		this.chunkY = y * Globals.CHUNK_SIZE;
		this.tileX = x * Globals.TILE_SIZE;
		this.tileY = y * Globals.TILE_SIZE;
	};

}
