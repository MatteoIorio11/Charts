package model;

public enum Coin {
	
	BITCOIN("BTC"),
	ETHERIUM_COIN("ETC"),
	DOGE_COIN("DOGE"),
	MONERO("XMR");
	
	
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
