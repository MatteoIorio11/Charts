package model;

import java.time.LocalTime;

public class Status {
	private Boolean status = false;
	
	public void changeStatus() {
		this.status = !this.status;
	}
	
	public Boolean getStatus() {
		return this.status;
	}
	
	public void delay(final long seconds) {
		final long now = LocalTime.now().getSecond();
		while(LocalTime.now().getSecond() - now <= seconds) {}
	}
}
