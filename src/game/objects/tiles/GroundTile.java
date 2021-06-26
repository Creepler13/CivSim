package game.objects.tiles;

import java.awt.event.MouseEvent;

import game.models.tiles.GroundTileModel;
import game.objectSupers.Model;
import game.objectSupers.Tile;

public class GroundTile extends Tile {

	public GroundTile() {
	}

	public Model model = new GroundTileModel();

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public void onTick() {
	}

	@Override
	public Boolean canBuildOn() {
		return null;
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		((GroundTileModel) model).changeToPink();
	}

}
