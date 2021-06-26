package game.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class KeyEventListener implements KeyListener {

	private static HashMap<Integer, HashMap<String, KeyEventHandler>> keyBindings = new HashMap<>();

	@Override

	public void keyPressed(KeyEvent e) {
		Set<Entry<String, KeyEventHandler>> set = getKeyHandlers(e);
		if (set != null)
			for (Entry<String, KeyEventHandler> entry : set) {
				entry.getValue().keyPressed(e, entry.getKey());
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Set<Entry<String, KeyEventHandler>> set = getKeyHandlers(e);
		if (set != null)
			for (Entry<String, KeyEventHandler> entry : set) {
				entry.getValue().keyReleased(e, entry.getKey());
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Set<Entry<String, KeyEventHandler>> set = getKeyHandlers(e);
		if (set != null)
			for (Entry<String, KeyEventHandler> entry : set) {
				entry.getValue().keyTyped(e, entry.getKey());
			}
	}

	private Set<Entry<String, KeyEventHandler>> getKeyHandlers(KeyEvent e) {
		HashMap<String, KeyEventHandler> bindingsOfKeyCode = keyBindings.get(e.getKeyCode());
		return bindingsOfKeyCode != null ? bindingsOfKeyCode.entrySet() : null;
	}

	public static void addKeyEventHandler(String bindingName, KeyEventHandler keyEventHandler, int keyCode) {
		if (!keyBindings.containsKey(keyCode)) {
			HashMap<String, KeyEventHandler> bind = new HashMap<>();
			bind.put(bindingName, keyEventHandler);
			keyBindings.put(keyCode, bind);
		} else {
			if (keyBindings.get(keyCode).putIfAbsent(bindingName, keyEventHandler) != null)
				System.err.println(bindingName + " for " + keyCode + " was already in use, so it was Skipped");
		}
	}

	public static void changeBinding(String bindingName, int newKeyCode) {

		int oldKeyCode = 0;

		for (Entry<Integer, HashMap<String, KeyEventHandler>> entry : keyBindings.entrySet()) {
			if (entry.getValue().keySet().contains(bindingName))
				oldKeyCode = entry.getKey();
		}

		addKeyEventHandler(bindingName, keyBindings.get(oldKeyCode).get(bindingName), newKeyCode);
		keyBindings.get(oldKeyCode).remove(bindingName);
	}

}
