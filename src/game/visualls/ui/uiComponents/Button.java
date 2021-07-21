package game.visualls.ui.uiComponents;

import java.awt.event.MouseEvent;

import game.registrys.ImageRegistry;
import game.visualls.ImageType;

public class Button extends UIComponent {

	public static final String STANDART = "standart";

	public String type = STANDART;

	public int state = 0;

	public Button(String type, int width, int heigth) {
		setSize(width, heigth);
		setImage(ImageRegistry.getImage(ImageType.UI, "button", type + 0), 16, 16);
		this.type = type;

	}

	@Override
	public void onMouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setImage(ImageRegistry.getImage(ImageType.UI, "button", type + 1), 16, 16);
	}

	@Override
	public void onMouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setImage(ImageRegistry.getImage(ImageType.UI, "button", type + 0), 16, 16);
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		setImage(ImageRegistry.getImage(ImageType.UI, "button", type + 2), 16, 16);
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		setImage(ImageRegistry.getImage(ImageType.UI, "button", type + 0), 16, 16);
	}

}
