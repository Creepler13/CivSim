package game.world;

import java.util.ArrayList;

public class Chunk {

	public int chunkX, chunkY;

	public Tile[] tiles;
	public ArrayList<Entity> entitys = new ArrayList<>();

	public Chunk(int x, int y) {
		this.chunkX = x;
		this.chunkY = y;
	}

}
