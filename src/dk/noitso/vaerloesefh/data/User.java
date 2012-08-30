package dk.noitso.vaerloesefh.data;

public class User {
	private String name;
	private long totalTimeInMs;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getTotalTimeInMs() {
		return totalTimeInMs;
	}
	
	public void setTotalTimeInMs(long totalTimeInMs) {
		this.totalTimeInMs = totalTimeInMs;
	}
}
