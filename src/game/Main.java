package game;

import game.controls.FrameEvent;
import game.registrys.ImageRegistry;
import game.registrys.ObjectRegistry;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class Main {

	public static Renderer rend;

	public static World world;

	public static void main(String[] args) {
		ObjectRegistry.loadObjects();
		ImageRegistry.loadImages();
		world = new World(100, 100);
		Window window = new Window(world);
		window.frame.addWindowListener(new FrameEvent());

		rend = new Renderer(window);

		SaveManager.loadGame("save");

		System.out.println(ImageRegistry.listImages());

		//world.addEntity(new Pig(), 100, 100);
		
		
		long wait = 1000 / Globals.TPS;

		while (true) {
			try {
				Thread.sleep(wait);
				world.tick();
				rend.update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
