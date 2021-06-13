package game.controls;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheel implements MouseWheelListener {

	public CameraControlls CC;

	public MouseWheel(CameraControlls CC) {
		this.CC = CC;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		this.CC.onMouseWheel(e);
	}

}
