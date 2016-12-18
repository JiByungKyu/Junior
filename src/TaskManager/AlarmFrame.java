package TaskManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AlarmFrame extends JFrame{
	/*
	 * 알람 frame
	 */
	public AlarmFrame(int indexOfTasks) {
		Task task=ToDoList.myTasks.get(indexOfTasks);
		this.setTitle(task.getName());
		this.setVisible(true);
		setSize(332,481);	//사이즈 정의
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)dimen.getWidth()/2 - this.getWidth()/2 - 100 , (int)dimen.getHeight()/2 - this.getHeight()/2);//켜졌을때의 프레임의 위치
		setResizable(false);//크기조정 불가
		getContentPane().setLayout(null);
		
		JLabel lblCat = new JLabel("Catergory : " + task.getCategory());
		lblCat.setBounds(100, 50, 200, 18);
		getContentPane().add(lblCat);
		JLabel lblField = new JLabel("Field : "+task.getField());
		lblField.setBounds(100, 150, 200, 18);
		getContentPane().add(lblField);
		
		JLabel lblName = new JLabel("Name : "+task.getName());
		lblName.setBounds(100, 100, 200, 18);
		getContentPane().add(lblName);
		
		JLabel lblNote = new JLabel("Note : "+ task.getNote());
		lblNote.setBounds(100, 200, 200, 18);
		getContentPane().add(lblNote);
		
		JLabel lblPrio = new JLabel("Priority : " + task.getPriority());
		lblPrio.setBounds(100, 250, 200, 18);
		getContentPane().add(lblPrio);
		
		JLabel lblStart = new JLabel("Start Date : "+task.getStartDate().toString());
		lblStart.setBounds(100, 300, 200, 18);
		getContentPane().add(lblStart);
	}
}