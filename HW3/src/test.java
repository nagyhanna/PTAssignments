import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat format= new SimpleDateFormat("hh:mm:ss");
		DateTime th =new DateTime();
		String start ="08:00:00";
		String stop = "21:00:00";
		System.out.println(th.toString("yyyy-MMM-dd"));
		System.out.println(th.getHourOfDay() +" : "+ th.getMinuteOfHour());
	
		Date d1=null;
		Date d2 =null;
		
		try {
			d1= format.parse(start);
			d2= format.parse(stop);
			DateTime dt1= new DateTime(d1);
			DateTime dt2= new DateTime(d2);
			dt2=dt2.plusMinutes(1);
			System.out.println(dt1.toString("hh:mm:ss")+ Hours.hoursBetween(dt1, dt2).getHours());
			System.out.println(dt2.toString("hh:mm:ss"));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	
	
	
	}

}
