
public class Appointment {
	public String description;
	public int year;
	public int month;
	public int day;
	
	Appointment(int year, int month, int day, String des)
	{
		this.year = year;
		this.month = month;
		this.day = day;
		this.description = des;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public int getMonth()
	{
		return this.month;
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	public String toString()
	{
		return this.description;
	}
	
	public boolean occursOn(int year, int month, int day)
	{
//		if(this.year != year)
//			return false;
//		if(this.month != month)
//			return false;
//		if(this.day != day)
//			return false;
//		
//		return true;
	}
	
	
	
	
}
