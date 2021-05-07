package game.world;

public abstract class Tile {

	public Build build;
	public World world;
	public Position position;

	public abstract Model getModel();

	public Tile(World world) {
		this.world = world;
	}

}
