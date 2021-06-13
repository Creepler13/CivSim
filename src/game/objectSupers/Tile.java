package game.objectSupers;

import game.world.Model;
import game.world.Position;
import game.world.World;

public abstract class Tile {

	public Build build;
	private Position position;

	public abstract Model getModel();

	public abstract void onTick(World world);

	public abstract Boolean canBuildOn();

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setTileEntity(TileEntity tileEntity) {
		this.tileEntiy = tileEntity;
	}

	public TileEntity getTileEntiy() {
		return tileEntiy;
	}

	private TileEntity tileEntiy;

	public boolean hasTileEntity() {
		return tileEntiy != null;
	}

	public void setPosition(int x, int y) {
		if (this.position == null) {
			this.position = new Position(x, y);
			return;
		}
		this.position.setPosition(x, y);
	}

	public Tile() {
	}

}
