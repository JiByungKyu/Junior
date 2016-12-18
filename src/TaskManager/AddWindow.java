package TaskManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 * Class used to add Task data to the ToDoList
 * @author Charles Henry
 *
 */
public class AddWindow extends JFrame implements ActionListener{
	
	private static JLabel taskName;
	private static JLabel startDate;
	private static JLabel time;
	private static JLabel priority;
	private static JLabel field;
	private static JLabel category;
	private static JLabel note;
	private static JTextField taskNameDisplay;
	private static JPanel startDatePanel;
	private static JPanel timePanel;
	private static JComboBox priorityCombo;
	private static JComboBox categoryCombo;
	private static JTextField noteDisplay;
	private static JPanel mainPanel;
	private static JPanel lowerPanel;
	private static JButton add;
	private static JButton cancel;
	
	private static JComboBox dayCombo;
	private static JComboBox monthCombo;
	private static JComboBox yearCombo;
	private static JComboBox hourCombo;
	private static JComboBox minCombo;
	
	private static JRadioButton insideBtn;
	private JRadioButton outdoorBtn;
	
	private static Calendar cal;
	
	private static String[] priorityList = {"Low", "Medium", "High", "Other"};
	private static String[] categoryList = {"Personal", "Social", "Business", "School", "Other"};
	private static String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private static String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private static String[] year;
	private static String[] hour;
	private static String[] min;
	private JPanel panel;
	
	/**
	 * Method used to create the Add window
	 */
	public AddWindow()
	{	
		int tYear;
		
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setLayout(new BorderLayout());
		this.setTitle("Add Task");
		this.setSize(350,220);
		this.setResizable(false);
		this.setLocation((int)dimen.getWidth()/2 - this.getWidth()/2, (int)dimen.getHeight()/2 - this.getHeight()/2);
		
		cal = Calendar.getInstance();
		year = new String[21];
		for(int i=0; i < 21; i++){
			tYear = cal.get(Calendar.YEAR);
			year[i] = Integer.toString(tYear - 10 + i);
		}
		min = new String[60];
		for(int i = 0; i < 60; i++){
			min[i] = Integer.toString(i);
		}
		hour = new String[24];
		for(int i = 1; i < 25; i++){
			hour[i-1] = Integer.toString(i);
		}
		
		taskName = new JLabel("Task Name: ");
		startDate = new JLabel("Start Date: ");
		time = new JLabel("Time:");
		priority = new JLabel("Priority: ");
		category = new JLabel("Category: ");
		note = new JLabel("Note: ");
		taskNameDisplay  = new JTextField("");
		startDatePanel 	 = new JPanel(new GridLayout(1,3));
		dayCombo = new JComboBox(day);
		monthCombo = new JComboBox(month);
		yearCombo = new JComboBox(year);
		startDatePanel.add(yearCombo);
		startDatePanel.add(monthCombo);
		startDatePanel.add(dayCombo);
		
		timePanel = new JPanel(new GridLayout(1,3));
		hourCombo = new JComboBox(hour);
		minCombo = new JComboBox(min);
		timePanel.add(hourCombo);
		timePanel.add(minCombo);
		
		priorityCombo 	 = new JComboBox(priorityList);
		categoryCombo  	 = new JComboBox(categoryList);
		noteDisplay  	 = new JTextField("");
		
		mainPanel = new JPanel(new GridLayout(7, 2));				//Create a panel with a grid layout
		mainPanel.add(taskName);		mainPanel.add(taskNameDisplay);
		mainPanel.add(startDate);		mainPanel.add(startDatePanel);
		field = new JLabel("Alarm:");
		mainPanel.add(field);
		
		panel = new JPanel();
		mainPanel.add(panel);
		panel.setLayout(null);
		
		outdoorBtn = new JRadioButton("No");
		outdoorBtn.setBounds(62, 0, 58, 23);
		outdoorBtn.setSelected(false);
		panel.add(outdoorBtn);
		
		insideBtn = new JRadioButton("Yes");
		insideBtn.setBounds(0, 0, 58, 23);
		insideBtn.setSelected(true);
		panel.add(insideBtn);
		mainPanel.add(time);			mainPanel.add(timePanel);
		mainPanel.add(priority);		
		mainPanel.add(priorityCombo);
		mainPanel.add(category);		mainPanel.add(categoryCombo);
		mainPanel.add(note);			mainPanel.add(noteDisplay);
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);	//Add the panel to the CENTER of the BorderLayout
		
		add = new JButton("Add");
		cancel = new JButton("Cancel");
		add.addActionListener(this);
		cancel.addActionListener(this);

		
		lowerPanel = new JPanel(new FlowLayout());
		lowerPanel.add(add);
		lowerPanel.add(cancel);
		this.getContentPane().add(BorderLayout.SOUTH, lowerPanel);	//Add the panel to the SOUTH of the BorderLayout
		/**
		 * Method used to clear the text fields when the window is closed
		 */
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
		    {
		        clearText();
		    }
		});
		clearText();
		validate();
	}
	
	/**
	 * Method used to reset the window to its default state, empty text fields etc
	 */
	public void clearText()
	{
		taskNameDisplay.setText("");
		
		dayCombo.setSelectedItem(Integer.toString(cal.get(Calendar.DATE)));
		monthCombo.setSelectedItem(Integer.toString(cal.get(Calendar.MONTH) + 1));
		yearCombo.setSelectedItem(Integer.toString(cal.get(Calendar.YEAR)));

		hourCombo.setSelectedItem(1);
		minCombo.setSelectedItem(0);
		
		priorityCombo.setSelectedItem("Other");
		categoryCombo.setSelectedItem("Personal");
		noteDisplay.setText("");
	}
	/**
	 * Method used to track actions performed
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add)
		{
			try
			{
				if(taskNameDisplay.getText().equals("") || noteDisplay.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill in all of the fields.");
				}
				else if(taskNameDisplay.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill in a number from '0' to '100' in the percentage field.");
				}
				else if (!insideBtn.isSelected() && !outdoorBtn.isSelected()){
					JOptionPane.showMessageDialog(null, "Please check field");
				}
				else if(outdoorBtn.isSelected())
				{
					ToDoList.myTasks.add(new Task(taskNameDisplay.getText(),
							new myDate(Integer.parseInt((String) yearCombo.getSelectedItem()), Integer.parseInt((String) monthCombo.getSelectedItem()), Integer.parseInt((String) dayCombo.getSelectedItem()),
							Integer.parseInt((String) hourCombo.getSelectedItem()), Integer.parseInt((String) minCombo.getSelectedItem())),
							(String) priorityCombo.getSelectedItem(), true, (String) categoryCombo.getSelectedItem(), noteDisplay.getText()));
					ToDoList.listModel.addElement(ToDoList.myTasks.get(ToDoList.myTasks.size()-1).getName());
					TaskPanel.listModel.addElement(ToDoList.myTasks.get(ToDoList.myTasks.size()-1).getName());
					clearText();
					ToDoList.add1.setVisible(false);
					ToDoList.exported = false;
					JOptionPane.showMessageDialog(null, "Task Added.");
				}
				else if(insideBtn.isSelected()){
					ToDoList.myTasks.add(new Task(taskNameDisplay.getText(),
							new myDate(Integer.parseInt((String) yearCombo.getSelectedItem()), Integer.parseInt((String) monthCombo.getSelectedItem()), Integer.parseInt((String) dayCombo.getSelectedItem()),
							Integer.parseInt((String) hourCombo.getSelectedItem()), Integer.parseInt((String) minCombo.getSelectedItem())),
							(String) priorityCombo.getSelectedItem(), false, (String) categoryCombo.getSelectedItem(), noteDisplay.getText()));
					ToDoList.listModel.addElement(ToDoList.myTasks.get(ToDoList.myTasks.size()-1).getName());
					TaskPanel.listModel.addElement(ToDoList.myTasks.get(ToDoList.myTasks.size()-1).getName());
					clearText();
					ToDoList.add1.setVisible(false);
					ToDoList.exported = false;
					JOptionPane.showMessageDialog(null, "Task Added.");
				}
			}
			catch(NumberFormatException n)
			{
				JOptionPane.showMessageDialog(null, "Please fill field.");
			}
		}
		if(e.getSource() == cancel)
		{
			clearText();
			ToDoList.add1.setVisible(false);
		}
	}
}