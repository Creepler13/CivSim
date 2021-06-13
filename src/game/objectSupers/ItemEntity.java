package game.objectSupers;

import game.world.Model;
import game.world.World;

public class ItemEntity extends Entity {

	public ItemStack itemStack;

	public ItemEntity(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	@Override
	public Model getModel() {
		System.out.println(this.itemStack);
		return this.itemStack.getItem().getModel();
	}

	@Override
	public void onTick(World world) {
	}

	@Override
	public void apply(ItemStack stack) {
	}

}
