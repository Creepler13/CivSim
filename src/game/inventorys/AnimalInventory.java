package game.inventorys;

import game.objectSupers.ItemStack;

public class AnimalInventory extends Inventory {

	@Override
	public int getSlotCount() {
		return 6;
	}

	@Override
	public Boolean canBeAdded(ItemStack stack) {
		return true;
	}

}
