package game.controls;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import game.visualls.ui.PauseMenu;

public class Controlls {

	public Controlls() {
		KeyEventListener.addKeyEventHandler("pauseMenu", new KeyEventHandler() {

			@Override
			public void keyTyped(KeyEvent e, String bindingName) {
				// TODO Auto-generated method stub

			}

			private boolean open = false;

			private PauseMenu menu = new PauseMenu();

			@Override
			public void keyReleased(KeyEvent e, String bindingName) {
				// TODO Auto-generated method stub
				if (!open) {
					menu.open();
					open = true;
				} else {
					menu.close();
					open = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e, String bindingName) {
				// TODO Auto-generated method stub

			}
		}, KeyEvent.VK_ESCAPE);
	}

	public void onMouseButtonClicked(MouseEvent e) {

	}

	public void onMouseMoved(MouseEvent e) {

	}

}
