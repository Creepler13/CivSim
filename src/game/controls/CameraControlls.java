package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import game.Globals;
import game.Main;
import game.visualls.Window;

public class CameraControlls {

	public Window window;

	public CameraControlls(Window window) {
		this.window = window;
		this.window.panel.addMouseMotionListener(new MouseMoved(this, window.C));
		this.window.panel.addMouseListener(new MouseButton(this));
		this.window.panel.addMouseWheelListener(new MouseWheel(this));
	}

	private int lastX = 0, lastY = 0;

	private Boolean start = true;

	public void onMouseRelease(MouseEvent e) {
		this.start = true;
	}

	public void onDrag(MouseEvent e) {
		if (!this.start) {

			int newX = (int) (((this.lastX - e.getX()) * this.window.camera.zoom + this.window.camera.pos.realX));
			int newY = (int) (((this.lastY - e.getY()) * this.window.camera.zoom + this.window.camera.pos.realY));

			if (newX < 0)
				newX = 0;
			if (newX > this.window.world.width * Globals.REAL_CHUNK_SIZE - this.window.camera.getWidth())
				newX = this.window.world.width * Globals.REAL_CHUNK_SIZE - this.window.camera.getWidth();
			if (newY < 0)
				newY = 0;
			if (newY > this.window.world.height * Globals.REAL_CHUNK_SIZE - this.window.camera.getHeight())
				newY = this.window.world.height * Globals.REAL_CHUNK_SIZE - this.window.camera.getHeight();
			this.window.camera.pos.setPosition(newX, newY);
			Main.rend.update();
		} else {
			this.start = false;
		}
		this.lastX = e.getX();
		this.lastY = e.getY();

	}

	public void onMouseWheel(MouseWheelEvent e) {
		double newZoom = this.window.camera.zoom + e.getPreciseWheelRotation() * e.getScrollAmount() / 30;
		if (newZoom >= 0.5 && newZoom <= 1.5)
			this.window.camera.zoom = newZoom;

		Main.rend.update();
	}

}
