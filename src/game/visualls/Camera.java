package game.visualls;

import game.world.Position;

public class Camera {

	public Position pos = new Position(0, 0);
	public double zoom = 1;
	private int width = 960, height = 540;

	public int getWidth() {
		return (int) (width * this.zoom);
	}

	public int getHeight() {
		return (int) (height * this.zoom);
	}

}
