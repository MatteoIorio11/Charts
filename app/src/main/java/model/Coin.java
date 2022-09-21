package model;

public enum Coin {
	
	BITCOIN("BTC"),
	ETHERIUM("ETC");
	
	private String name;
	
	Coin(final String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
