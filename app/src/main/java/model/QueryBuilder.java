package model;

public class QueryBuilder {
	private final static String API = "https://api.minerstat.com/v2/coins?";
	private String list ="list=";
	
	public QueryBuilder() {
		
	}
	
	public QueryBuilder selectCoin(final Coin coin) {
		if(this.list.equals("list=")) {
			this.list += coin.getName();
		}else {
			this.list += ",";
			this.list += coin.getName();
		}
		return this;
	}
	
	public QueryBuilder peekRandom() {
		/*Select a random coin*/
		/*If in list != "list=" throw exception */
		/*Add the random coin at the query */
		return this;
	}
	
	public String build() {
		return API + list;
	}
	
}
