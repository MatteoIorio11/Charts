package model;

public enum Coin {
	
	BITCOIN("BTC"),
	ETHERIUM("ETH");
	
	private String name;
	
	Coin(final String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
