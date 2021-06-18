package game.objectSupers;

import game.world.Position;
import game.world.World;

public abstract class Entity extends GameObject {

	public abstract Model getModel();

	public abstract void onTick(World world);

	public abstract void apply(ItemStack stack);

	@Override
	public void setPosition(Position position) {
		super.setPosition(position.isEntity() ? position : new Position(position.realX, position.realY, this));
	}

}
