package game.objectSupers;

import game.world.Position;
import game.world.World;

public abstract class TileEntity extends Entity {

	public TileEntity() {
	}

	private Tile tile;

	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		tile = World.getTile(getPosition());
		tile.setTileEntity(this);
	}

	@Override
	public void setPosition(Position position) {
		super.setPosition(position);
		tile = World.getTile(getPosition());
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
