import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

	private BlockingQueue<Task> blockingQueue;
	private AtomicInteger waitingTime;

	public Server() {
		blockingQueue = new LinkedBlockingQueue<Task>();
		waitingTime = new AtomicInteger(0);
	}

	@Override
	public void run() {

		while (true) {
			try {

				Task currentTask = blockingQueue.take();
				Thread.sleep(currentTask.getProcessTime() * 1000);
				this.waitingTime.addAndGet((-1) * currentTask.getProcessTime());

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}

	}

	public void addTask(Task t) {

		blockingQueue.add(t); // put
		waitingTime.addAndGet(t.getProcessTime());
	}

	public Task[] getTasks() {

		Task[] tasks = new Task[blockingQueue.size()];
		blockingQueue.toArray(tasks);
		return tasks;
	}
	public AtomicInteger getWaitingTime(){
		return waitingTime;
	}

}
