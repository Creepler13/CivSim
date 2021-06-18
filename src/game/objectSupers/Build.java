package game.objectSupers;

public abstract class Build extends GameObject {

	public abstract Model getModel();

	public abstract void onTick();

	public abstract Boolean canBuildOn();

	public Tile tile;

}
