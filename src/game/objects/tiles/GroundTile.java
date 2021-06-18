package game.objects.tiles;

import game.models.tiles.GroundTileModel;
import game.objectSupers.Model;
import game.objectSupers.Tile;
import game.world.World;

public class GroundTile extends Tile {

	public GroundTile() {
	}

	public Model model = new GroundTileModel();

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public void onTick(World world) {
	}

	@Override
	public Boolean canBuildOn() {
		return null;
	}

}
