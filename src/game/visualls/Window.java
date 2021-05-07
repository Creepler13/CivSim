package game.visualls;

import javax.swing.JFrame;

import game.world.World;

public class Window {

	public JFrame frame;
	public JBackgroundPanel panel;
	public World world;
	public Camera camera;

	public Window(World world) {
		this.world = world;
		this.camera = new Camera();
		this.frame = new JFrame();
		this.frame.setBounds(0,0,500,500);
		this.panel = new JBackgroundPanel();
		this.frame.add(panel);
		this.frame.setVisible(true);
	}

}
