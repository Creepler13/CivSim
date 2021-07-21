package game.visualls.ui.uiComponents;

import java.awt.Image;
import java.awt.event.MouseEvent;

import game.registrys.ImageRegistry;
import game.visualls.ImageType;

public class Button extends UIComponent {

	public static final String STANDART = "standart";

	public String type = STANDART;

	public int state = 0;

	private int width, heigth;

	public Button(String type, int width, int heigth) {
		this.type = type;
		this.heigth = heigth;
		this.width = width;

	}

	@Override
	public Image getBackground() {
		return ImageRegistry.getImage(ImageType.UI, "button", type + state);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return heigth;
	}

	@Override
	public void onMouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		state = 1;
	}

	@Override
	public void onMouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		state = 0;
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Button");
		state = 2;
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		System.out.println("Button");
		state = 1;
	}

	@Override
	public int getResourceWidth() {
		return 16;
	}

	@Override
	public int getResourceHeight() {
		// TODO Auto-generated method stub
		return 16;
	}

}
