package test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestDate {
	public static void main(String [] args) {
		Date d = new Date();
		System.out.println(d.toString());
		GregorianCalendar c = new GregorianCalendar();
		c.add(Calendar.HOUR, -1);
		
		d = c.getTime();
		System.out.println(d);
	}
}
