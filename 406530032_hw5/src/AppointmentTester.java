import java.util.ArrayList;

public class AppointmentTester {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Appointment> appointment = new ArrayList<Appointment>();
		
		
	}
	
	public Appointment addAppointment(int year, int month, int day, String des, String type)
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
	
	public void displayAppointment(int sy, int sm, int sd, int ey, int em, int ed)
	{
		for(int y = sy; y <= ey; y++)
			for(int m = sm; m <= em; m++)
				for(int d = sd; d <= ed; d++)
				{
					if(Onetime.occursOn(y, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Onetime", y, m, d, Onetime.showDescription(y, m, d));
					if(Daily.occursOn(y, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Daily", y, m, d, Daily.showDescription(y, m, d));
					if(Monthly.occursOn(y, m, d))
						System.out.printf("%s : %4d %2d %2d\t%s\n","Monthly", y, m, d, Monthly.showDescription(y, m, d));
					
					if(Daily.occursOn(y, m, d) || Onetime.occursOn(y, m, d) || Monthly.occursOn(y, m, d))
						System.out.println("\n");
				}
	}
	/**
	 * it cannot recover 
	 * @param y
	 * @param m
	 * @param d
	 * @param appointment
	 */
	public void deleteAppointment(int y, int m, int d, ArrayList appointment)
	{
		Onetime.deleteApp(y, m, d);
		Daily.deleteApp(y, m, d);
		Monthly.deleteApp(y, m, d);
	}
	
	
	
	
	
}
