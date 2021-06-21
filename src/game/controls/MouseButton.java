package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseButton implements MouseListener {

	public CameraControlls CC;

	public MouseButton(CameraControlls CC) {
		this.CC = CC;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.CC.onMouseRelease(e);
	}

}
