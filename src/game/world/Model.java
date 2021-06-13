package game.world;

import java.awt.Image;

public interface Model {

	public abstract Image getImage();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract int getInGameWidth();

	public abstract int getInGameHeight();
	
	public abstract int getXOffset();

	public abstract int getYOffset();

	
}
