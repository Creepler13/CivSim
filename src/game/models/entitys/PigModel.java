package game.models.entitys;

import java.awt.Image;

import game.registrys.ImageRegistry;
import game.visualls.ImageType;
import game.world.Model;

public class PigModel implements Model {

	@Override
	public Image getImage() {
		return ImageRegistry.getImage(ImageType.ENTITY, "pig");
	}

	@Override
	public int getWidth() {
		return 8;
	}

	@Override
	public int getHeight() {
		return 8;
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
