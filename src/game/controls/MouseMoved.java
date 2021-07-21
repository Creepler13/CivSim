package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import game.visualls.ui.uiComponents.Button;
import game.visualls.ui.uiComponents.UI;
import game.visualls.ui.uiComponents.UIComponent;

public class MouseMoved implements MouseMotionListener {

	public MouseMoved() {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (GameObjectMouseEventHandler.isOnUI(e) == null)
			CameraControlls.onDrag(e);
	}

	private UIComponent lastOn = new Button("a", 0, 0);

	@Override
	public void mouseMoved(MouseEvent e) {
		UI ui = GameObjectMouseEventHandler.isOnUI(e);
		UIComponent comp;

		if (ui != null) {
			comp = GameObjectMouseEventHandler.getUIComponent(ui, e);

			if (comp != lastOn) {
				lastOn.onMouseExited(e);
				comp.onMouseEntered(e);
				lastOn = comp;
			}
		}

	}

}
