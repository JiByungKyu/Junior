package JDP;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(){
		super("Task Manager");
		
		MenuBar menu = new MenuBar();
		ContentPanel content = new ContentPanel(); 
		
		setSize(830,510);//사이즈 정의
		setLocation(270,90);//켜졌을때의 프레임의 위치
		//setResizable(false);//크기조정 불가
		
		setJMenuBar(menu);//위쪽에 메뉴 추가
		add(content);//중앙에 content 추가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//export tasks on close
		addWindowListener(new WindowAdapter(){
			/**
			 * Method used to export tasks when the program is closed
			 * 
			 * @ param exported boolean used to show whether the tasks have been saved of not
			 */
			public void windowClosing(WindowEvent we)
			    {
					content.pn_task.toDoList.exportTasks();
			    }
		});
	}
}
