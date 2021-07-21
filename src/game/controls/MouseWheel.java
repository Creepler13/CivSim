package game.controls;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import game.visualls.ui.uiComponents.UI;

public class MouseWheel implements MouseWheelListener {

	public MouseWheel() {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		UI ui = GameObjectMouseEventHandler.isOnUI(e);
		if(ui==null) {
		CameraControlls.onMouseWheel(e);
		}else {
			GameObjectMouseEventHandler.getUIComponent(ui, e).onMouseWheelMoved(e);
		}
	}

}
