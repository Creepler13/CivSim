package game.world;

public abstract class Entity {

	public Position postion;

	public World world;

	public abstract Model getModel();

	public Entity(int x, int y, World world) {
		this.world = world;
		this.postion = new Position(x, y, world, this);
	};

}
