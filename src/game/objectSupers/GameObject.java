package game.objectSupers;

import java.awt.event.MouseEvent;

import game.world.Position;

public class GameObject {

	int reach = 0;

	public boolean isInReach(GameObject gameobject) {
		return true;// todo calc if is in reach
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public int getReach() {
		return reach;
	}

	private Position position = Position.fresh();

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position.setPosition(x, y);
	}

	public void onMouseClicked(MouseEvent e) {
	}

	public void onMousePressed(MouseEvent e) {
	}

	public void onMouseReleased(MouseEvent e) {
	}

	public void onMouseEntered(MouseEvent e) {
	}

	public void onMouseExited(MouseEvent e) {
	}

}
