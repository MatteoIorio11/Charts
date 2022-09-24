package model;

public class Coin {

	private final String name;
	
	public Coin(final String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
