import java.util.ArrayList;
import java.util.List;

public class Scheduler {

	private List<Server> servers;
	private List<Thread> threads;
	private Reader reader;
	private int nrOfServers;
	private int sumWaitingTime;
	private int sumAllWaitingTime = 0;
	private int howManyTasks = 0;
	private int peakHourMaxWT = Integer.MIN_VALUE;
	// private List<Integer> peakHours;
	private int time = 0;

	public Scheduler() {
		reader = new Reader();
		// peakHours= new ArrayList<Integer>();
		nrOfServers = reader.getNrOfServers();

		servers = new ArrayList<Server>();
		threads = new ArrayList<Thread>();

		for (int i = 0; i < nrOfServers; i++) {
			servers.add(new Server());
			Thread thread = new Thread(servers.get(i));
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.start();
		}
	}

	public int min(int[] size) {
		int i = 0;
		int j = 0;
		int min = Integer.MAX_VALUE;
		while (i < size.length) {
			if (min > size[i]) {
				min = size[i];
				j = i;
			}
			i++;
		}
		return j;
	}

	public void dispatchTaskOnServer(Task t, int currentTime) {

		int[] sizes = new int[servers.size()];
		for (int i = 0; i < servers.size(); i++) {
			sizes[i] = servers.get(i).getTasks().length;
		}
		int i = 0;
		howManyTasks++;
		boolean isAdded = false;
		while (!isAdded && i < servers.size()) {

			if (sizes[i] <= 2) {

				int waitingTime = calculateWaitingTimePerOneQueue(i, sizes[i]);
				if (peakHourMaxWT < waitingTime) {
					peakHourMaxWT = waitingTime;
					setTime(currentTime);
				}
				System.out.println("currentTimein Sch:" + currentTime);

				servers.get(i).addTask(t);
				System.out.println("task added in the queue: " + i);
				isAdded = true;
				break;
			}
			if (i == servers.size() - 1) {

				int waitingTime = calculateWaitingTimePerOneQueue(i, sizes[i]);

				if (peakHourMaxWT < waitingTime) {
					peakHourMaxWT = waitingTime;
					setTime(currentTime);
				}
				servers.get(min(sizes)).addTask(t);
				System.out.println("task added in the queue: " + min(sizes));

			}

			i++;
		}

	}

	public int calculateAverageWaitingTime() {
		return sumAllWaitingTime / howManyTasks;
	}

	public int calculateWaitingTimePerOneQueue(int i, int size) {

		sumWaitingTime = 0;

		Task[] tasks = new Task[size];
		tasks = servers.get(i).getTasks();

		int j = 0;
		while (j < tasks.length) {
			sumWaitingTime += tasks[j].getProcessTime();
			sumAllWaitingTime += sumWaitingTime;
			j++;
		}
		System.out.println("waiting time " + sumWaitingTime + " for queue: " + i);
		return sumWaitingTime;
	}

	public List<Server> getServers() {
		return servers;
	}

	public int getPeakHourMaxWT() {
		return peakHourMaxWT;
	}

	public void setPeakHourMaxWT(int peakHourMaxWT) {
		this.peakHourMaxWT = peakHourMaxWT;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
