package game.objectSupers;

public class ItemStack {

	private Item item;
	private int count = 0;

	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}

	public int getCount() {
		return this.count;
	};

	public int getStackLimit() {
		return this.item.getStackLimit();
	};
	
	public Item getItem() {
		return this.item;
	}

	public void setCount(int i) {
		this.count = i;
	};

	@Override
	public String toString() {
		return item + ":" + count;
	}

}
