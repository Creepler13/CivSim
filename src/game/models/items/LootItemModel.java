package game.models.items;

import java.awt.Image;

import game.objectSupers.Item;
import game.objectSupers.Model;
import game.registrys.ImageRegistry;
import game.visualls.ImageType;

public class LootItemModel implements Model {

	private Item item;

	public LootItemModel(Item item) {
		this.item = item;
	}

	@Override
	public Image getImage() {
		return ImageRegistry.getImage(ImageType.ITEM, item.getName());
	}

	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 16;
	}

	@Override
	public int getXOffset() {
		return 0;
	}

	@Override
	public int getYOffset() {
		return 0;
	}

	@Override
	public int getInGameWidth() {
		return 8;
	}

	@Override
	public int getInGameHeight() {
		return 8;
	}

}
