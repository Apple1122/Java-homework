import java.util.ArrayList;

import javax.naming.ldap.Rdn;

import sun.security.provider.JavaKeyStore.CaseExactJKS;

public class AppointmentTester {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Appointment> appointment = new ArrayList<Appointment>();
		
		addAppointment(2018, 3, 28, "java appointment", "D");
		addAppointment(2018, 4, 3, "hello", "M");
		addAppointment(2018, 4, 3, "Onetime", "O");
		deleteAppointment(2018, 3, 4, appointment);
		deleteAppointment(2018, 4, 4, appointment);
		displayAppointment(2018, 3, 26, 2019, 3, 28);
		
		
	}
	
	public static Appointment addAppointment(int year, int month, int day, String des, String type)
	{
		if(type.equals("O"))
		{
			Onetime app = new Onetime(year, month, day, des);
			return app;
		}
		else if(type.equals("M"))
		{
			Monthly app = new Monthly(year, month, day, des);
			return app;
		}
		else if(type.equals("D"))
		{
			Daily app = new Daily(year, month, day, des);
			return app;
		}
		else
		{
			Appointment app = null;
			return app;
		}
	}
	public static void displayAppointment(int sy, int sm, int d, ArrayList<Appointment> appointment)
	{
		if(Onetime.occursOn(sy, sm, d))
			System.out.printf("%s : %4d %2d %2d\t%s\n","Onetime", sy, sm, d, Onetime.showDescription(sy, sm, d));
		if(Daily.occursOn(sy, sm, d))
			System.out.printf("%s : %4d %2d %2d\t%s\n","Daily", sy, sm, d, Daily.showDescription(sy, sm, d));
		if(Monthly.occursOn(sy, sm, d))
			System.out.printf("%s : %4d %2d %2d\t%s\n","Monthly", sy, sm, d, Monthly.showDescription(sy, sm, d));
	}
	/**
	 * for loop have problem cannot run all year around, month around(1 ~ 12) and day around(1 ~ 30 or 31)
	 * how to end at end date
	 * @param sy
	 * @param sm
	 * @param sd
	 * @param ey
	 * @param em
	 * @param ed
	 */
	public static void displayAppointment(int sy, int sm, int sd, int ey, int em, int ed)
	{
		// first round: acount start day to judgeDay(sm)
		for(int d = sd; d < judgeDay(sy, sm) || (d <= ed && sy == ey && sm == em); d++)
		{
			if(Onetime.occursOn(sy, sm, d))
				System.out.printf("%s : %4d %2d %2d\t%s\n","Onetime", sy, sm, d, Onetime.showDescription(sy, sm, d));
			if(Daily.occursOn(sy, sm, d))
				System.out.printf("%s : %4d %2d %2d\t%s\n","Daily", sy, sm, d, Daily.showDescription(sy, sm, d));
			if(Monthly.occursOn(sy, sm, d))
				System.out.printf("%s : %4d %2d %2d\t%s\n","Monthly", sy, sm, d, Monthly.showDescription(sy, sm, d));

			if(Daily.occursOn(sy, sm, d) || Onetime.occursOn(sy, sm, d) || Monthly.occursOn(sy, sm, d))
				System.out.println("\n");
		}
		//second round: after accounting first month, then account m = sm + 1 until first year done
			for(int m = sm + 1; m <= 12 || (m <= em && sy == ey) ; m++)
				for(int d = 1; d <= judgeDay(sy, m); d++)
				{
					if(Onetime.occursOn(sy, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Onetime", sy, m, d, Onetime.showDescription(sy, m, d));
					if(Daily.occursOn(sy, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Daily", sy, m, d, Daily.showDescription(sy, m, d));
					if(Monthly.occursOn(sy, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Monthly", sy, m, d, Monthly.showDescription(sy, m, d));
					
					if(Daily.occursOn(sy, m, d) || Onetime.occursOn(sy, m, d) || Monthly.occursOn(sy, m, d))
						System.out.println("\n");
				}
		//after round: account after first year
			int y = 0;
			int m = 0;
			int d = 0;
			
		for(y = sy + 1; y <= ey; y++)
		{
			for(m = 1; m <= 12; m++)
			{
				for(d = 1; d <= judgeDay(y, m); d++)
				{
					if(y == ey && m == em && d == ed)
						break;
					
					if(Onetime.occursOn(y, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Onetime", y, m, d, Onetime.showDescription(y, m, d));
					if(Daily.occursOn(y, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Daily", y, m, d, Daily.showDescription(y, m, d));
					if(Monthly.occursOn(y, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Monthly", y, m, d, Monthly.showDescription(y, m, d));
					
					if(Daily.occursOn(y, m, d) || Onetime.occursOn(y, m, d) || Monthly.occursOn(y, m, d))
						System.out.println("\n");
				}
				if(y == ey && m == em && d == ed)
					break;
			}
			if(y == ey && m == em && d == ed)
				break;
		}
			
	}
	/**
	 * it cannot recover 
	 * @param y
	 * @param m
	 * @param d
	 * @param appointment
	 */
	public static void deleteAppointment(int y, int m, int d, ArrayList appointment)
	{
		Onetime.deleteApp(y, m, d);
		Daily.deleteApp(y, m, d);
		Monthly.deleteApp(y, m, d);
	}
	/**
	 	公元年分除以4不可整除，為平年。
		公元年分除以4可整除但除以100不可整除，為閏年。
		公元年分除以100可整除但除以400不可整除，為平年。
		公元年分除以400可整除但除以3200不可整除，為閏年。
		公元年分除以3200可整除，為平年。
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	public static int judgeDay(int y, int m)
	{
		int day = 0;
		
		switch(m)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;			
			case 4:
			case 6:
			case 9:
			case 11:
				day = 30;
				break;
			case 2:
				if(y % 4 == 0)
				{
					if(y % 100 == 0)
						if(y % 400 == 0)
							day = 29;
						else
							day = 28;
					else 
						day = 29;
				}
				else {
					day = 28;
				};
				break;
		}
		
		return day;
	}
	
	
	
	
}
