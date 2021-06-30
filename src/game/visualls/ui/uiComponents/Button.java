package game.visualls.ui.uiComponents;

import java.awt.Image;
import java.awt.event.MouseEvent;

import game.registrys.ImageRegistry;
import game.visualls.ImageType;

public class Button extends UIComponent {

	public static final String STANDART = "standart";

	public String type = STANDART;

	public int state = 0;

	private int x, y, width, heigth;

	public Button(String type, int x, int y, int width, int heigth) {
		this.type = type;
		this.heigth = heigth;
		this.width = width;
		this.x = x;
		this.y = y;
	}

	@Override
	public Image getBackground() {
		return ImageRegistry.getImage(ImageType.UI, "button", type + state);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
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
	public void onMouseEntered(MouseEvent e, UIComponent comp) {
		// TODO Auto-generated method stub
		state = 1;
	}

	@Override
	public void onMouseExited(MouseEvent e, UIComponent comp) {
		// TODO Auto-generated method stub
		state = 0;
	}

	@Override
	public void onMousePressed(MouseEvent e, UIComponent comp) {
		// TODO Auto-generated method stub
		state = 2;
	}

	@Override
	public void onMouseReleased(MouseEvent e, UIComponent comp) {
		// TODO Auto-generated method stub
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
