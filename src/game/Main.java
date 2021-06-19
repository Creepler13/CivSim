package game;

import game.controls.FrameEvent;
import game.registrys.ImageRegistry;
import game.registrys.ObjectRegistry;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class Main {

	public static Window window;

	public static void main(String[] args) {
		ObjectRegistry.loadObjects();
		ImageRegistry.loadImages();
		World.init(100, 100);
		Window.frame.addWindowListener(new FrameEvent());

		SaveManager.loadGame("save");

		System.out.println(ImageRegistry.listImages());

		// world.addEntity(new Pig(), 100, 100);

		long wait = 1000 / Globals.TPS;

		while (true) {
			try {
				Thread.sleep(wait);
				World.tick();
				Renderer.update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
