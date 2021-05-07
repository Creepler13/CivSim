package game.objects.models.tiles;

import java.awt.Image;

import game.visualls.ImageLoader;
import game.visualls.ImageType;
import game.world.Model;

public class GroundTileModel implements Model {

	@Override
	public Image getImage() {
		return ImageLoader.getImage(ImageType.TILE, "ground");
	}

	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 16;
	}

}
