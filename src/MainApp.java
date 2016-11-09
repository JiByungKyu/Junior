import java.io.IOException;

/**
 * Class used to run the ToDoList application
 * 
 * @author Charles Henry
 *
 */
public class MainApp {

	public static void main(String[] args) throws IOException {
		// ToDoList ToDo1 = new ToDoList();
		// ToDo1.setVisible(true); // Makes the frame visible to the user
		// (default
		// = false)
		ForecastExplorer a = new ForecastExplorer();
		a.send();
	}

}