package game.objects.items;

import game.models.items.LootItemModel;
import game.objectSupers.Item;
import game.world.Model;

public class Bone extends Item {

	public Model model = new LootItemModel(this);

	@Override
	public int getStackLimit() {
		return 8;
	}

	@Override
	public String getName() {
		return "bone";
	}

	@Override
	public Model getModel() {
		return this.model;
	}

}
