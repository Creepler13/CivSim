package game.objects.tiles;

import game.objects.models.tiles.GroundTileModel;
import game.world.Model;
import game.world.Tile;
import game.world.World;

public class GroundTile extends Tile {

	public GroundTile( World world) {
		super(world);
	}

	public Model model = new GroundTileModel();

	@Override
	public Model getModel() {
		return this.model;
	}

}
