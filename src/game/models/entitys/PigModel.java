package game.models.entitys;

import java.awt.Image;

import game.objectSupers.Model;
import game.registrys.ImageRegistry;
import game.visualls.ImageType;

public class PigModel implements Model {

	@Override
	public Image getImage() {
		return ImageRegistry.getImage(ImageType.ENTITY, "pig", "pig1");
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
