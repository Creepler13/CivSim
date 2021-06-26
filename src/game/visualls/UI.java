package game.visualls;

import java.awt.Image;
import java.awt.event.MouseEvent;

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

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract int getX();

	public abstract int getY();

	public void onMouseClicked(MouseEvent e) {

	}

	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
