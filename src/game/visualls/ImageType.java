package game.visualls;

public enum ImageType {

	BUILD("builds"), ENTITY("entitys"), TILE("tiles"), ITEM("items"), UI("ui");

	private String s;

	private ImageType(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return this.s;
	}

}
