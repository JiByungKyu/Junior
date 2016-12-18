package TaskManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener {
	//field
	private JMenu mn_task;
	private JMenu mn_info;
	private JMenuItem add_task;
	private JMenuItem edit_task;
	private JMenuItem delete_task;
	private JMenuItem show_all;
	private JMenuItem show_info;
	//method
	public MenuBar(){
		mn_task = new JMenu("Edit");
		mn_info = new JMenu("Info");
		
		add_task = new JMenuItem("Add Task");
		edit_task = new JMenuItem("Edit Task");
		delete_task = new JMenuItem("Delete Task");
		show_all = new JMenuItem("Show All");
		show_info = new JMenuItem("List");
		
		add_task.addActionListener(this);
		edit_task.addActionListener(this);
		delete_task.addActionListener(this);
		show_all.addActionListener(this);
		show_info.addActionListener(this);
		
		mn_task.add(add_task);
		mn_task.add(edit_task);
		mn_task.add(delete_task);
		mn_task.add(show_all);
		mn_info.add(show_info);
		
		add(mn_task);
		add(mn_info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add_task){
			TaskPanel.toDoList.add1.setVisible(true);
		}
		else if(e.getSource() == edit_task){
			TaskPanel.toDoList.edit(TaskPanel.taskList.getSelectedIndex());
		}
		else if(e.getSource() == delete_task){
			TaskPanel.toDoList.delete(TaskPanel.taskList.getSelectedIndex());
		}
		else if(e.getSource() == show_all){
			TaskPanel.toDoList.setVisible(true);
		}
		else if(e.getSource() == show_info){
			if(MainFrame.subison == true){
				MainFrame.smain.setVisible(false);
				MainFrame.subison = false;
			}else{
				MainFrame.smain.setVisible(true);
				MainFrame.subison = true;
			}
		}
	}
}
