package dk.noitso.chrono.data;

public class User {
	private String name;
	private long totalTimeInMs;
	private int[] timeAvay = new int[21];
	
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
		this.timeAvay[0] = (int)totalTimeInMs;
	}
	
	public int getTimeAtPos(int i){
		return timeAvay[i];
	}
	
	public int[] getTimeArray(){
		return timeAvay;
	}
	public void setTimeInArray(int pos, int value){
		timeAvay[pos] = value;
	}
	public String getTimeArrayAsCSVFormat(){
		String hitMe="";
		for(int i : timeAvay){
			hitMe+=";"+i;
		}
		return hitMe;
	}
}
