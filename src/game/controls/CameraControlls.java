package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import game.Globals;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class CameraControlls {

	public CameraControlls() {

		Window.panel.addMouseMotionListener(new MouseMoved(this, Window.C));
		Window.panel.addMouseListener(new MouseButton(this));
		Window.panel.addMouseWheelListener(new MouseWheel(this));
		Window.panel.addMouseListener(new GameObjectMouseEventHandler());
		Window.panel.addKeyListener(new KeyEventListener());
	}

	private int lastX = 0, lastY = 0;

	private Boolean start = true;

	public void onMouseRelease(MouseEvent e) {
		this.start = true;
	}

	public void onDrag(MouseEvent e) {
		if (!this.start) {

			int newX = (int) (((this.lastX - e.getX()) * Window.camera.zoom + Window.camera.pos.realX));
			int newY = (int) (((this.lastY - e.getY()) * Window.camera.zoom + Window.camera.pos.realY));

			if (newX < 0)
				newX = 0;
			if (newX > World.width * Globals.REAL_CHUNK_SIZE - Window.camera.getWidth())
				newX = World.width * Globals.REAL_CHUNK_SIZE - Window.camera.getWidth();
			if (newY < 0)
				newY = 0;
			if (newY > World.height * Globals.REAL_CHUNK_SIZE - Window.camera.getHeight())
				newY = World.height * Globals.REAL_CHUNK_SIZE - Window.camera.getHeight();
			Window.camera.pos.setPosition(newX, newY);
			Renderer.update();
		} else {
			this.start = false;
		}
		this.lastX = e.getX();
		this.lastY = e.getY();

	}

	public void onMouseWheel(MouseWheelEvent e) {
		double newZoom = Window.camera.zoom + e.getPreciseWheelRotation() * e.getScrollAmount() / 30;
		if (newZoom >= 0.5 && newZoom <= 1.5)
			Window.camera.zoom = newZoom;

		Renderer.update();
	}

}
