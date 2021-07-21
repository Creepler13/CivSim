package game.visualls.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

import game.registrys.ImageRegistry;
import game.visualls.ImageType;
import game.visualls.ui.uiComponents.Button;
import game.visualls.ui.uiComponents.UI;

public class PauseMenu extends UI {

	public PauseMenu() {

		Button button = new Button(Button.STANDART, 200, 200);
		addComponent(button, 50, 50);

	}

	@Override
	public Image getBackground() {
		return ImageRegistry.getImage(ImageType.ENTITY, "pig", "pig");
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public int getResourceWidth() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public int getResourceHeight() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public void onUIOpened(UI ui) {
		System.out.println("pause");
	}

	@Override
	public void onUIClosed(UI ui) {
		System.out.println("unpaused");
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		System.out.println("hit");
	}

}
