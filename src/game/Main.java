package game;

import game.visualls.ImageLoader;
import game.visualls.Renderer;
import game.visualls.Window;
import game.world.World;

public class Main {

	public static void main(String[] args) {
	ImageLoader.loadImages();
		World world = new World(100, 100);
		Window window = new Window(world);
		Renderer rend = new Renderer(window);
		rend.update();
	}

}
