package model;

public class QueryBuilder {
	private final static String API = "https://api.minerstat.com/v2/coins?";
	private String list ="list=";
	private String qeury = "";
	
	public QueryBuilder() {
		
	}
	
	public QueryBuilder selectCoin(final Coin coin) {
		if(this.list.isBlank()) {
			this.list = String.join(this.list, coin.getName());
		}else {
			this.list = String.join(this.list, ",");
			this.list = String.join(this.list, coin.getName());
		}
		return this;
	}
	
	public QueryBuilder peekRandom() {
		/*Select a random coin*/
		/*Add the random coin at the query */
		return this;
	}
	
	public String build() {
		
		return String.join(API, list);
	}
	
}
