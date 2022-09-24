package model;


public class Status {
	private int cont= 0;
	
	public void changeStatus() {
		this.cont++;
	}
	
	public int getStatus() {
		return this.cont;
	}
}
