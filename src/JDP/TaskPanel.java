package JDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import task.AddWindow;
import task.EditWindow;
import task.ToDoList;

public class TaskPanel extends JPanel implements ActionListener,Runnable {
	//field
	JPanel pn_taskList;
	JPanel pn_button;
	JPanel pn_showbtn;
	JList taskList;
	DefaultListModel listModel;
	JButton btn_add;
	JButton btn_delete;
	JButton btn_modify;
	JButton btn_showAll;
	AddWindow addTask;
	EditWindow editTask;
	ToDoList toDoList;
	
	//method
	TaskPanel(){
		pn_taskList = new JPanel();
		pn_button = new JPanel();
		pn_showbtn = new JPanel();
		listModel = new DefaultListModel();
		taskList = new JList(listModel);
		btn_add = new JButton("일정 추가");
		btn_modify = new JButton("수정");
		btn_delete = new JButton("삭제");
		btn_showAll = new JButton("일정 모두 보기");
		addTask = new AddWindow();
		editTask = new EditWindow();
		toDoList = new ToDoList();	
		
		setLayout(new BorderLayout());
		pn_button.setBackground(Color.WHITE);
		pn_showbtn.setBackground(Color.WHITE);
		
		taskList.setFixedCellWidth(250);
		JScrollPane scroller = new JScrollPane(taskList);//스크롤 생성
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskList.setVisibleRowCount(10);//10줄까지만 보이도록 설정
		taskList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pn_taskList.setBackground(Color.WHITE);
		pn_taskList.add(scroller);
		
		btn_add.addActionListener(this);
		btn_modify.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_showAll.addActionListener(this);
		
		pn_button.add(btn_add);
		pn_button.add(btn_modify);
		pn_button.add(btn_delete);
		pn_showbtn.add(btn_showAll);
		
		add(pn_taskList,"North");
		add(new JPanel());
		add(pn_button,"Center");
		add(pn_showbtn,"South");
	
		showTask();
		//export tasks on close
				this.addWindowListener(new WindowAdapter(){
					/**
					 * Method used to export tasks when the program is closed
					 * 
					 * @ param exported boolean used to show whether the tasks have been saved of not
					 */
					public void windowClosing(WindowEvent we)
				    {
						if(!toDoList.getExported())	//if tasks are not saved already
						{
							toDoList.exportTasks();
						}
				    }
				});
	}

	public void export(){
		toDoList.exportTasks();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String event = e.getActionCommand();
		if(event.equals("일정 추가"))
			addTask.setVisible(true);
		else if(event.equals("수정"))
			editTask.setVisible(true);
		else if(event.equals("삭제"))
			;
		else if(event.equals("일정 모두 보기")){
			toDoList.setVisible(true);
		}
	}
	
	public void showTask(){
		for(int i = 0; i < toDoList.getTask().size(); i++)
			listModel.addElement(toDoList.getTask().get(i).getName());
	}
}
