package TaskManager;

/**
 * A Task within a TaskList
 *
 * @author Charles Henry
 */
public class Task {

	private String name;
	private myDate startDate;
	private boolean field;
	private String priority;
	private String category;
	private String note;

	/**
	 * Constructor used to make a generic task
	 */
	public Task()
	{
		this.name = "name";
		this.startDate = new myDate(2016, 01, 01, 1, 0);
		this.priority = "priority";
		this.field = false;
		this.category = "category";
		this.note = "note";
	}
	/**
	 * Method used to create a task
	 * @param name task name
	 * @param startDate	task start date
	 * @param endDate task end date
	 * @param priority task priority
	 * @param percComp task percentage completed
	 * @param category task category
	 * @param note task notes
	 */
	public Task(String name, myDate startDate, String priority, boolean field, String category, String note)
	{
		this.name = name;
		this.startDate = startDate;
		this.priority = priority;
		this.field = field;
		this.category = category;
		this.note = note;
	}
	/**
	 * Method used to return the name of the task
	 * @return the task's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Method used to set the task's name
	 * @param category the task's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Method used to return the start date of the task
	 * @return the task's start date
	 */
	public myDate getStartDate() {
		return startDate;
	}
	/**
	 * Method used to set the task's start date
	 * @param category the task's start date
	 */
	public void setStartDate(myDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * Method used to return the task's priority
	 * @return the task's priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * Method used to set the task's priority
	 * @param category the task's priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public boolean getField(){
		return field;
	}
	
	public void setField(boolean field){
		this.field = field;
	}
	
	/**
	 * Method used to set the task's category
	 * @param category the task's category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * Method used to return the category of the task
	 * @return the task's category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Method used to set the task notes
	 * @param category the task notes
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * Method used to return the task notes
	 * @return the task notes
	 */
	public String getNote() {
		return note;
	}
	/**
	 * Method used to return a textual representation of the task
	 * @return the representation of the task
	 */
	public String toString() {
		return "Name: "+ getName() + "\n" + "Start Date: " + getStartDate() + "\n" + "Time: " + getStartDate().timeToString() + "\n" +
	"Priority: "+ getPriority() + "\n" + "Field" + getField()  + "\n" + "Percent Complete: " + "Category: " + getCategory() + "Note: " + getNote();
	}
	/**
	 * Method used to output the task data used for exporting into files
	 * @return task data
	 */
	public String toStringExport() {
		return getName() +"�"+ getStartDate().getYear() +"�"+ getStartDate().getMonth() +"�"+ getStartDate().getDay()+"�"+
				+ getStartDate().getHour() +"�"+ getStartDate().getMin() +"�"+ getPriority() +"�"+getField() +"�"+ getCategory() +"�"+ getNote();
	}
}
