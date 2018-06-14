import java.util.ArrayList;

public class Onetime extends Appointment{

	private static ArrayList<Onetime> app = new ArrayList<Onetime>();
	
	Onetime(int year, int month, int day, String des) {
		super(year, month, day, des);
		// TODO Auto-generated constructor stub
		
		app.add(this);
	}
	
	public static boolean occursOn(int year, int month, int day)
	{
		for(Onetime d : app)
			if(d.day == day && d.month == month && d.year == year)
				return true;
		return false;
	}
	
	public static String showDescription(int year, int month, int day)
	{
		String des = "";
		
		for(Onetime d1 : app)
			if(d1.occursOn(d1.year, d1.month, d1.day))
				des += d1.description + " \t";
		return des;
	}
	
	public static void deleteApp(int y, int m, int d)
	{
		ArrayList<Onetime > temp = new ArrayList<Onetime>();
				
		for(Onetime del : app)
		{
			if(del.day == d && del.month == m && del.year == y) ;
			
			else
				temp.add(del);
		}
		
		app.clear();
		app = new ArrayList<Onetime>(temp);
	}
}
