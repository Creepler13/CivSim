package game.visualls;

import javax.swing.JFrame;

import game.controls.CameraControlls;
import game.controls.Controlls;
import game.controls.FrameEvent;
import game.world.World;

public class Window {

	public static JFrame frame;
	public static JBackgroundPanel panel;
	public World world;
	public static Camera camera;
	public static CameraControlls CC;
	public static Controlls C;

	public static void init() {
		camera = new Camera();
		frame = new JFrame();
		frame.setBounds(0, 0, 960 + 16, 540 + 35);
		frame.setResizable(false);
		panel = new JBackgroundPanel();
		frame.add(panel);
		frame.setVisible(true);

		panel.grabFocus();

		C = new Controlls();
		CC = new CameraControlls();

		frame.addWindowListener(new FrameEvent());
	}

	public static void setResolution(Resolution res) {
		frame.setBounds(frame.getX(), frame.getWidth(), res.width + 16, res.height + 35);
	}

}
