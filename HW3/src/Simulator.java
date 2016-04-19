import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.joda.time.DateTime;

public class Simulator implements Runnable {
	// generator taskuri noi;

	// private int timeLimit = 56; // 20:00
	private String start;// = "08:00:00";
	private String stop;// = "13:00:00";
	private int minProcTime;
	private int maxProcTime;
	private Scheduler scheduler;
	private SimulatorFrame frame;
	private Reader reader;

	public Simulator() {

		scheduler = new Scheduler();
		frame = new SimulatorFrame();
		reader = new Reader();
		minProcTime = reader.getMinProcTime();
		maxProcTime = reader.getMaxProcTime();
		start=reader.getStartTime();
		stop=reader.getStopTime();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// joda time
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		Date date1 = null;
		Date date2 = null;

		try {
			date1 = format.parse(start);
			date2 = format.parse(stop);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		DateTime dateStart = new DateTime(date1);
		DateTime dateStop = new DateTime(date2);

		DateTime currentTime = dateStart;
		int sumProcessTime = 0;
		int k = 0;
		while (currentTime.getHourOfDay() < dateStop.getHourOfDay()) {

			int processingTime = (int) (Math.random() * (maxProcTime - minProcTime) + minProcTime);
			sumProcessTime += processingTime;
			Task task = new Task(currentTime.getHourOfDay(), processingTime);

			System.out.println("current Time Hours: " + currentTime.getHourOfDay());
			scheduler.dispatchTaskOnServer(task, currentTime.getHourOfDay());

			List<Task[]> listOfTasks = new ArrayList<Task[]>();

			for (Server server : scheduler.getServers()) {
				listOfTasks.add(server.getTasks());

			}

			frame.displayData(listOfTasks);

			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			currentTime = currentTime.plusMinutes(5); // currentTime+=5 minute
			System.out.println(currentTime.getHourOfDay() + ": " + currentTime.getMinuteOfHour() + ": "
					+ currentTime.getSecondOfMinute());
			k++;
		}

		System.out.println("AVERAGE PROCESSING TIME of a Server = " + sumProcessTime / k);
		System.out.println("AVERAGE WAITING TIME for all the queues = " + scheduler.calculateAverageWaitingTime());
		System.out.println("Peak Hour = " + scheduler.getTime() + " when the max waiting time was= "
				+ scheduler.getPeakHourMaxWT());
		System.out.println("Today were = " + k + " clients served!");

	}

	public static void main(String[] args) {

		Simulator simulator = new Simulator();
		Thread th = new Thread(simulator);
		th.start();

	}

}
