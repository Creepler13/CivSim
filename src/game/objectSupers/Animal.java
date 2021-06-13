package game.objectSupers;

import game.inventorys.AnimalInventory;
import game.world.Model;
import game.world.World;

public abstract class Animal extends Entity {

	public AnimalInventory inventory = new AnimalInventory();

	@Override
	public abstract Model getModel();

	@Override
	public void onTick(World world) {

	}

	@Override
	public abstract void apply(ItemStack stack);

}
