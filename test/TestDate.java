package test;

import java.util.Date;
import java.util.GregorianCalendar;

public class TestDate {
	public static void main(String [] args) {
		Date d = new Date();
		System.out.println(d.toString());
		GregorianCalendar calendar = new GregorianCalendar();
		Date d2 = calendar.getTime();
		System.out.println(d2.toString());
	}
}
