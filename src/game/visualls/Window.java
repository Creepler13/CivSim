package game.visualls;

import javax.swing.JFrame;

import game.controls.CameraControlls;
import game.controls.Controlls;
import game.controls.MouseMoved;
import game.world.World;

public class Window {

	public JFrame frame;
	public JBackgroundPanel panel;
	public World world;
	public Camera camera;
	public CameraControlls CC;
	public Controlls C;

	public Window(World world) {
		this.world = world;
		this.camera = new Camera();
		this.frame = new JFrame();
		this.frame.setBounds(0, 0, 960 + 16, 540 + 35);
		this.frame.setResizable(false);
		this.panel = new JBackgroundPanel();
		this.frame.add(panel);
		this.frame.setVisible(true);
		
		C = new Controlls(this);
		CC = new CameraControlls(this);
	}

}
