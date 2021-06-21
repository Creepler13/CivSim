package game.visualls;

import java.awt.Image;
import java.awt.event.MouseEvent;

import game.world.Position;

public abstract class UI {

	public void open() {
		Renderer.openUI(this);
	}

	public void close() {
		Renderer.closeUI(this);
	}

	public int getUILayer() {
		return Renderer.openUIs.indexOf(this);
	}

	public abstract Image getUI();

	public abstract Position getPosition();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract int getX();

	public abstract int getY();

	public abstract void onClicked(MouseEvent e);

	public abstract void onPessed(MouseEvent e);

	public abstract void onReleased(MouseEvent e);

}
