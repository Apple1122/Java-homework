import java.util.ArrayList;

public class Monthly extends Appointment{
	
	private static ArrayList<Monthly> app = new ArrayList<Monthly>();
	private static ArrayList<Appointment> del = new ArrayList<Appointment>();
	
	Monthly(int year, int month, int day, String des) {
		super(year, month, day, des);
		// TODO Auto-generated constructor stub
		app.add(this);
	}
	
	public static boolean occursOn(int year, int month, int day)
	{
		for(Monthly d : app)
		{
			for(Appointment de : del)
			{
				if(de.year < year)
					return false;
				if(de.year <= year && de.month <= month)
					return false;
			}

			if(d.day == day && d.year == year)
				return true;
		}
		return false;
	}

	public static String showDescription(int year, int month, int day)
	{
		String des = null;
		
		for(Monthly d1 : app)
			if(d1.occursOn(d1.year, d1.month, d1.day))
				des += d1.description + " \t";
		return des;
	}
	
	public static void deleteApp(int y , int m, int d)
	{
		del.add(new Appointment(y, m, d, ""));
	}
	
}
