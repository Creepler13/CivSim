package game;

import java.awt.event.KeyEvent;

import game.controls.FrameEvent;
import game.controls.KeyBinds;
import game.controls.KeyEventHandler;
import game.controls.KeyEventListener;
import game.registrys.ImageRegistry;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class Main {

	public static void main(String[] args) {
		ImageRegistry.loadImages();
		World.init(100, 100);
		Window.init();
		KeyBinds.init();
		Window.frame.addWindowListener(new FrameEvent());

	

		// World.addEntity(new Pig(), 100, 100);

		
		KeyEventListener.addKeyEventHandler("", new KeyEventHandler() {
			
			@Override
			public void keyTyped(KeyEvent e, String bindingName) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e, String bindingName) {
				// TODO Auto-generated method stub
				Renderer.ImageToFile(Renderer.i, "drawBackground");
			}
			
			@Override
			public void keyPressed(KeyEvent e, String bindingName) {
				// TODO Auto-generated method stub
				
			}
		}, KeyEvent.VK_4);
		
		SaveManager.loadGame("save");
		
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
