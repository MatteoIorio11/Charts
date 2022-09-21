package model;

public class Status {
	private Boolean status = false;
	
	public void changeStatus() {
		this.status = !this.status;
	}
	
	public Boolean getStatus() {
		return this.status;
	}
}
