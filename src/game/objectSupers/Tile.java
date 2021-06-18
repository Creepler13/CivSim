package game.objectSupers;

import game.world.World;

public abstract class Tile extends GameObject {

	private Build build;

	public abstract Model getModel();

	public abstract void onTick(World world);

	public abstract Boolean canBuildOn();

	public boolean hasBuild() {
		return this.build != null;
	}

	public Build getBuild() {
		return this.build;
	}

	public void setBuild(Build build) {
		this.build = build;
		this.build.setPosition(getPosition());
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
}
