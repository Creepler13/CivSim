package game.visualls;

import java.awt.Graphics2D;

public abstract class UI {

	public UI() {

	}

	public void open() {
		Renderer.openUI(this);
	}

	public void close() {
		Renderer.closeUI(this);
	}

	public abstract void drawUI(Graphics2D g);

}
