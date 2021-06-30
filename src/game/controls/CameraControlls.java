package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import game.Globals;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class CameraControlls {

	public CameraControlls() {
		Window.panel.addMouseMotionListener(new MouseMoved());
		Window.panel.addMouseWheelListener(new MouseWheel());
		Window.panel.addMouseListener(new GameObjectMouseEventHandler());
		Window.panel.addKeyListener(new KeyEventListener());
	}

	private static int lastX = 0;

	private static int lastY = 0;

	static Boolean start = true;

	public static boolean enableMapDrag = true;

	public static void onMouseRelease(MouseEvent e) {
		if (enableMapDrag)
			start = true;
	}

	public static void onDrag(MouseEvent e) {
		if (!start) {

			int newX = (int) (((lastX - e.getX()) * Window.camera.zoom + Window.camera.pos.realX));
			int newY = (int) (((lastY - e.getY()) * Window.camera.zoom + Window.camera.pos.realY));

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
			start = false;
		}
		lastX = e.getX();
		lastY = e.getY();

	}

	public static void onMouseWheel(MouseWheelEvent e) {
		double newZoom = Window.camera.zoom + e.getPreciseWheelRotation() * e.getScrollAmount() / 30;
		if (newZoom >= 0.5 && newZoom <= 1.5)
			Window.camera.zoom = newZoom;

		Renderer.update();
	}

}
