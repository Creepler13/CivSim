package game.models.tiles;

import java.awt.Image;

import game.Globals;
import game.objectSupers.Model;
import game.registrys.ImageRegistry;
import game.visualls.ImageType;

public class GroundTileModel implements Model {

	@Override
	public Image getImage() {
		return ImageRegistry.getImage(ImageType.TILE, "ground");
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
		return Globals.TILE_SIZE;
	}

	@Override
	public int getInGameHeight() {
		return Globals.TILE_SIZE;
	}

}
