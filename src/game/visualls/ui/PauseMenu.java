package game.visualls.ui;

import java.awt.event.MouseEvent;

import game.registrys.ImageRegistry;
import game.visualls.ImageType;
import game.visualls.ui.uiComponents.Button;
import game.visualls.ui.uiComponents.UI;

public class PauseMenu extends UI {

	public PauseMenu() {
		setPosition(100, 100);
		setSize(500, 500);
		setImage(ImageRegistry.getImage(ImageType.ENTITY, "pig", "pig1"), 16, 16);

		Button button = new Button(Button.STANDART, 200, 200);
		addComponent(button, 50, 50);

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
