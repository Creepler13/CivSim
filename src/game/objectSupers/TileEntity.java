package game.objectSupers;

import game.Main;
import game.world.Model;
import game.world.Position;

public abstract class TileEntity extends Entity {

	public TileEntity() {
	}

	private Tile tile;

	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		tile = Main.world.getTile(getPosition());
		tile.setTileEntity(this);
	}

	@Override
	public void setPosition(Position position) {
		super.setPosition(position);
		tile = Main.world.getTile(getPosition());
		tile.setTileEntity(this);
	}

	@Override
	public Model getModel() {
		return null;
	}

	public Tile getTile() {
		return tile;
	}

}
