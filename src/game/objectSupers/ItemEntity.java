package game.objectSupers;

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
	public void onTick() {
	}

	@Override
	public void apply(ItemStack stack) {
	}

}
