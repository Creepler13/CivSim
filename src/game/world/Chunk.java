package game.world;

import java.util.ArrayList;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.Tile;
import game.objects.tiles.GroundTile;

public class Chunk {

	private Position position;
	public World world;

	public Tile[] tiles = new Tile[Globals.CHUNK_SIZE * Globals.CHUNK_SIZE];
	public ArrayList<Entity> entitys = new ArrayList<>();

	public Chunk(int x, int y, World world) {
		this.world = world;
		this.position = new Position(x, y);

		for (int i = 0; i < Globals.CHUNK_SIZE; i++) {
			for (int j = 0; j < Globals.CHUNK_SIZE; j++) {
				this.setTile(i, j, new GroundTile());
			}
		}

	}

	public void setTile(Position pos, Tile tile) {
		tiles[pos.tileX + pos.tileY * Globals.CHUNK_SIZE] = tile;
		tiles[pos.tileX + pos.tileY * Globals.CHUNK_SIZE].setPosition(
				this.position.realX + pos.tileX * Globals.TILE_SIZE,
				this.position.realY + pos.tileY * Globals.TILE_SIZE);

	}

	public void setTile(int x, int y, Tile tile) {
		tiles[x + y * Globals.CHUNK_SIZE] = tile;
		tiles[x + y * Globals.CHUNK_SIZE].setPosition(this.position.realX + x * Globals.TILE_SIZE,
				this.position.realY + y * Globals.TILE_SIZE);
	}

	public Tile getTile(Position pos) {
		return tiles[pos.tileX + pos.tileY * Globals.CHUNK_SIZE];
	}

	public Tile getTile(int x, int y) {
		return tiles[x + y * Globals.CHUNK_SIZE];
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position.setPosition(x, y);
	}

	@Override
	public String toString() {
		return "x:" + getPosition().chunkX + " y:" + getPosition().chunkY + " Entitys:" + this.entitys.size();
	}

}
