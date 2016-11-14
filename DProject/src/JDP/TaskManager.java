package JDP;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class TaskManager {
	public static void main(String[] args)throws IOException, ParseException{
		ForecastParser FP= new ForecastParser();
		MainFrame main = new MainFrame();
		Submain Sm = new Submain(FP);
		

	}
}

/*
public static void main(String[] args) throws IOException, ParseException {
	// ToDoList ToDo1 = new ToDoList();
	// ToDo1.setVisible(true); // Makes the frame visible to the user
	// (default
	// = false)
	Weather weather = newther();
}
*/

