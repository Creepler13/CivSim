package game.objectSupers;

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
		System.out.println(obj.getClass() + " " + this.getClass()); //$NON-NLS-1$
		if (obj.getClass() == this.getClass())
			return true;
		return false;
	}
}
