package game.objectSupers;

import game.world.Model;
import game.world.Position;

public abstract class Build {

	public abstract Model getModel();

	public abstract void onTick();

	public abstract Boolean canBuildOn();

	public Tile tile;
	private Position position;

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position.setPosition(x, y);
	}

	public Build() {
	}

}
