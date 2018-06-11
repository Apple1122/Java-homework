import java.util.ArrayList;

public class AppointmentTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Appointment addAppointment(int year, int month, int day, String description, String type)
	{
		Appointment app = new Appointment(year, month, day, description);
		
		switch(type)
		{
			case "O":
				app = (Onetime)app;
				break;
			case "D":
				app = (Daily)app;
				break;
			case "M":
				app = (Monthly)app;
				break;
		}
		
		return app;
	}
	
	public void displayAppointment(int year, int month, int day, ArrayList<Appointment> appointment)
	{
		
	}
	
	public void deleteAppointment(int year, int month, int day, ArrayList appointment)
	{
		
	}
	
	
	
	
	
}
