package game.objectSupers;

import game.inventorys.AnimalInventory;

public abstract class Animal extends Entity {

	public AnimalInventory inventory = new AnimalInventory();

	@Override
	public abstract Model getModel();

	@Override
	public void onTick() {

	}

	@Override
	public abstract void apply(ItemStack stack);

}
