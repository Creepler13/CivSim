package game;

import game.controls.FrameEvent;
import game.registrys.ImageRegistry;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class Main {

	public static void main(String[] args) {
		ImageRegistry.loadImages();
		World.init(100, 100);
		Window.init();
		Window.frame.addWindowListener(new FrameEvent());

		SaveManager.loadGame("save");

		// World.addEntity(new Pig(), 100, 100);

		long tickWait = 1000 / Globals.TPS;
		long frameWait = 1000 / Globals.FPS;

		Thread gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(tickWait);
						World.tick();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread renderThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(frameWait);
						Renderer.update();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		gameThread.start();
		renderThread.start();

	}

}
