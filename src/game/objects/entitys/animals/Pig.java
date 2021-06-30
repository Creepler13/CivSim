package game.objects.entitys.animals;

import game.models.entitys.PigModel;
import game.objectSupers.Animal;
import game.objectSupers.ItemEntity;
import game.objectSupers.ItemStack;
import game.objectSupers.Model;
import game.world.World;

public class Pig extends Animal {

	public Model model = new PigModel();

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public void onTick() {
		this.setPosition(getPosition().realX + 1, getPosition().realY);

	}

	public void onDeath() {
		World.removeEntity(this);
		for (int i = 0; i < inventory.getSlotCount(); i++) {
			ItemStack stack = inventory.getItem(i);
			if (stack != null) {
				World.addEntity(new ItemEntity(stack), getPosition());
			}
		}

	}

	@Override
	public void apply(ItemStack stack) {

	}

}
