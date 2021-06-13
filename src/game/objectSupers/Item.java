package game.objectSupers;

import game.world.Model;

public abstract class Item {

	public abstract int getStackLimit();

	public abstract String getName();

	public abstract Model getModel();

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println(obj.getClass() + "  " + this.getClass());
		if (obj.getClass() == this.getClass())
			return true;
		return false;
	}
}