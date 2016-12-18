package TaskManager;
/**
 * Class used to create date objects
 * @author Charles Henry
 *
 */
public class myDate {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	
	/**
	 * Method used to create a date instance
	 * @param day the day of this instance
	 * @param month the month of this instance
	 * @param year the year of this instance
	 */
	public myDate(int year, int month, int day, int hour, int min) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.min = min;
	}
	/**
	 * Method used to return the day of this instance
	 * @return the day of this instance
	 */
	public int getDay() {
		return day;
	}
	/**
	 * Method used to set the day of this instance
	 * @param day the day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * Method used to return the month of this instance
	 * @return the month of this instance
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * Method used to set the month of this instance
	 * @param month the month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * Method used to return the year of this instance
	 * @return the year of this instance
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Method used to set the year of this instance
	 * @param year the year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	public int getHour(){
		return hour;
	}
	public void setHour(int hour){
		this.hour = hour;
	}
	public int getMin(){
		return min;
	}
	public void setMin(int min){
		this.min = min;
	}
	@Override
	/**
	 * Method used to return a textual representation of this date instance
	 * @return the representation of this date instance
	 */
	public String toString() {
		return year + "/" + month + "/" + day;
	}
	public String timeToString(){
		return hour + "/" + min;
	}
}
