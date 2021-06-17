package game.objectSupers;

import game.world.Model;
import game.world.Position;
import game.world.World;

public abstract class Entity {

	private Position position;

	public abstract Model getModel();

	public abstract void onTick(World world);

	public abstract void apply(ItemStack stack);

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position.isEntity() ? position : new Position(position.realX, position.realY, this);
	}

	public void setPosition(int x, int y) {
		this.position.setPosition(x, y);
	}

	public Entity() {
	};

}
