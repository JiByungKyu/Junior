package JDP;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	//field
	private JMenu mn_task;
	private JMenu mn_tag;
	private JMenu mn_info;
	private JMenuItem add_task;
	private JMenuItem delete_task;
	private JMenuItem show_task;
	private JMenuItem add_tag;
	private JMenuItem delete_tag;
	private JMenuItem show_tag;
	private JMenuItem show_infolist;
	//method
	public MenuBar(){
		mn_task = new JMenu("Edit");
		mn_tag = new JMenu("Tag");
		mn_info = new JMenu("Info");
		
		add_task = new JMenuItem("Add Task");
		delete_task = new JMenuItem("Delete Task");
		show_task = new JMenuItem("Show Task");
		add_tag = new JMenuItem("Add Tag");
		delete_tag = new JMenuItem("Delete Tag");
		show_tag = new JMenuItem("Show Tag");
		show_infolist = new JMenuItem("List");
		
		mn_task.add(add_task);
		mn_task.add(delete_task);
		mn_task.add(show_task);
		mn_tag.add(add_tag);
		mn_tag.add(delete_tag);
		mn_tag.add(show_tag);
		mn_info.add(show_infolist);
		
		add(mn_task);
		add(mn_tag);
		add(mn_info);
	}
}
