
public class Task {

	private int arrivalTime;
	private int processTime;

	public Task(int currentTime, int processingTime) {
		this.arrivalTime = currentTime;
		this.processTime = processingTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public String toString() {
		return "arrival " + String.valueOf(arrivalTime) + "  process " + String.valueOf(processTime);
	}
}
