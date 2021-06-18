package game.objects.entitys.animals;

import game.models.entitys.PigModel;
import game.objectSupers.Animal;
import game.objectSupers.ItemEntity;
import game.objectSupers.ItemStack;
import game.objectSupers.Model;
import game.world.World;

public class Pig extends Animal {

	public Pig() {

	}

	public Model model = new PigModel();

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public void onTick(World world) {
		this.setPosition(getPosition().realX + 1, getPosition().realY);
		if (this.getPosition().realX == 150)
			onDeath(world);
	}

	public void onDeath(World world) {
		world.removeEntity(this);
		for (int i = 0; i < inventory.getSlotCount(); i++) {
			ItemStack stack = inventory.getItem(i);
			if (stack != null) {
				world.addEntity(new ItemEntity(stack), getPosition());
			}
		}

		
	}

	@Override
	public void apply(ItemStack stack) {

	}

}
