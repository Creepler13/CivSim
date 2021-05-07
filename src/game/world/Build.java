package game.world;

public abstract class Build {

	public abstract Model getModel();

	public Tile tile;
	public Position position;

	public Build(Tile tile) {
		this.tile = tile;
		this.position = new Position(tile.position.realX, tile.position.realY);
	}

}
