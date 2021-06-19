package game.inventorys;

import game.objectSupers.ItemStack;

public abstract class Inventory {

	private ItemStack[] items = new ItemStack[getSlotCount()];

	public abstract int getSlotCount();

	public abstract Boolean canBeAdded(ItemStack stack);

	public ItemStack add(ItemStack stack) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = stack;
				return null;
			} else if (items[i].getItem() == stack.getItem() && items[i].getStackLimit() > items[i].getCount()) {
				int count = stack.getCount() + items[i].getCount() - items[i].getStackLimit();
				if (count > 0) {
					items[i].setCount(items[i].getStackLimit());
					stack.setCount(count);
					return add(stack);
				} else {
					items[i].setCount(items[i].getStackLimit() + count);
					return null;
				}
			}
		}
		return stack;
	}

	public ItemStack getItem(int index) {
		return items[index];
	}

	@Override
	public String toString() {
		String s = "";
		for (ItemStack itemStack : items) {
			s = s + itemStack + ", ";
		}
		return s;
	}

}
