package game.controls;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheel implements MouseWheelListener {

	public MouseWheel() {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		CameraControlls.onMouseWheel(e);
	}

}
