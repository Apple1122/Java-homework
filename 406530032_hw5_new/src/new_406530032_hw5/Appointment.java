package new_406530032_hw5;

public class Appointment {
	public String description;
	public int year;
	public int month;
	public int day;
	public String type;
	
	Appointment(int year, int month, int day, String des, String type)
	{
		this.year = year;
		this.month = month;
		this.day = day;
		this.description = des;
		this.type = type;
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
	
	public static boolean occursOn(int year, int month, int day)
	{
		return false;
	}
	
	
	
	
}