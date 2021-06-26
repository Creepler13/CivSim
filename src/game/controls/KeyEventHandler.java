package game.controls;

import java.awt.event.KeyEvent;

public abstract interface KeyEventHandler {

	public abstract void keyPressed(KeyEvent e, String bindingName);

	public abstract void keyReleased(KeyEvent e, String bindingName);

	public abstract void keyTyped(KeyEvent e, String bindingName);

}
