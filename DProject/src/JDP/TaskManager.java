package JDP;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import HDP.Submain;

public class TaskManager {
	public static void main(String[] args)throws IOException, ParseException{
		
		MainFrame main = new MainFrame();
		
		Submain Sm = new Submain(main.getFP());


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

