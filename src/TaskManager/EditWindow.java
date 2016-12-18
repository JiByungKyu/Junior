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
 * Class used to edit Task data to the ToDoList
 * @author Charles Henry
 *
 */
public class EditWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private static JPanel fieldDisplay;
	private static JComboBox categoryCombo;
	private static JTextField noteDisplay;
	private static JPanel mainPanel;
	private static JPanel lowerPanel;
	private static JButton save;
	private static JButton cancel;
	
	private static JComboBox dayCombo;
	private static JComboBox monthCombo;
	private static JComboBox yearCombo;
	private static JComboBox hourCombo;
	private static JComboBox minCombo;

	private static Calendar cal;

	private static String[] priorityList = {"Low", "Medium", "High", "Other"};
	private static String[] categoryList = {"Personal", "Social", "Business", "School", "Other"};
	private static String[] year;
	private static String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private static String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private static String[] hour;
	private static String[] min;
	
	private static JRadioButton insideBtn;
	private static JRadioButton outdoorBtn;
	
	
	public EditWindow()
	{
		int tYear; 
		
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setLayout(new BorderLayout());
		this.setTitle("Edit Task");
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
		field = new JLabel("Field:");
		category = new JLabel("Category: ");
		note = new JLabel("Note: ");
		
		taskNameDisplay  = new JTextField();
		startDatePanel 	 = new JPanel(new GridLayout(1,3));
		dayCombo = new JComboBox(day);
		monthCombo = new JComboBox(month);
		yearCombo = new JComboBox(year);
		startDatePanel.add(yearCombo);
		startDatePanel.add(monthCombo);
		startDatePanel.add(dayCombo);
		
		timePanel 	 = new JPanel(new GridLayout(1,3));
		hourCombo = new JComboBox(hour);
		minCombo = new JComboBox(min);
		timePanel.add(hourCombo);
		timePanel.add(minCombo);
		
		priorityCombo 	 = new JComboBox(priorityList);
		categoryCombo  	 = new JComboBox(categoryList);
		noteDisplay  	 = new JTextField();
		
		mainPanel = new JPanel(new GridLayout(7, 2));				//Create a panel with a grid layout
		mainPanel.add(taskName);	mainPanel.add(taskNameDisplay);
		mainPanel.add(startDate);	mainPanel.add(startDatePanel);
		mainPanel.add(time);		mainPanel.add(timePanel);
		mainPanel.add(priority);	
		mainPanel.add(priorityCombo);
		mainPanel.add(field);
		
		fieldDisplay = new JPanel();
		mainPanel.add(fieldDisplay);
		fieldDisplay.setLayout(null);
		insideBtn = new JRadioButton("Inside");
		insideBtn.setBounds(0, 0, 75, 23);
		insideBtn.setSelected(true);
		fieldDisplay.add(insideBtn);
		
		outdoorBtn = new JRadioButton("Outdoor");
		outdoorBtn.setBounds(79, 0, 75, 23);
		outdoorBtn.setSelected(false);
		fieldDisplay.add(outdoorBtn);
		mainPanel.add(category);	mainPanel.add(categoryCombo);
		mainPanel.add(note);		mainPanel.add(noteDisplay);
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);	//Add the panel to the CENTER of the BorderLayout
		
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		lowerPanel = new JPanel(new FlowLayout());
		lowerPanel.add(save);
		lowerPanel.add(cancel);
		this.getContentPane().add(BorderLayout.SOUTH, lowerPanel);	//Add the panel to the SOUTH of the BorderLayout
		
		this.addWindowListener(new WindowAdapter()
		{
			/**
			 * Method used to manage variables when the task window is closed
			 */
		    public void windowClosing(WindowEvent we)
		    {
		        ToDoList.listenerOn = true;	//turn the JList listener on as editing has ended
		    }
		});
		
		validate();
	}
	/**
	 * Method used to take task information and populate the window using the information
	 * so that the user can edit the task
	 */
	public static void populate()
	{
		taskNameDisplay.setText(ToDoList.myTasks.get(ToDoList.currentEditIndex).getName());
		
		yearCombo.setSelectedItem(""+ToDoList.myTasks.get(ToDoList.currentEditIndex).getStartDate().getYear());
		monthCombo.setSelectedItem(""+ToDoList.myTasks.get(ToDoList.currentEditIndex).getStartDate().getMonth());
		dayCombo.setSelectedItem(""+ToDoList.myTasks.get(ToDoList.currentEditIndex).getStartDate().getDay());

		hourCombo.setSelectedItem(""+ToDoList.myTasks.get(ToDoList.currentEditIndex).getStartDate().getHour());
		minCombo.setSelectedItem(""+ToDoList.myTasks.get(ToDoList.currentEditIndex).getStartDate().getMin());

		
		priorityCombo.setSelectedItem(ToDoList.myTasks.get(ToDoList.currentEditIndex).getPriority());
		if(ToDoList.myTasks.get(ToDoList.currentEditIndex).getField())
			outdoorBtn.setSelected(true);
		else
			insideBtn.setSelected(true);
		categoryCombo.setSelectedItem(ToDoList.myTasks.get(ToDoList.currentEditIndex).getCategory());
		noteDisplay.setText(ToDoList.myTasks.get(ToDoList.currentEditIndex).getNote());
	}
	/**
	 * Method to track actions performed
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save)
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
				else
				{
					ToDoList.myTasks.get(ToDoList.currentEditIndex).setName(taskNameDisplay.getText());
					ToDoList.myTasks.get(ToDoList.currentEditIndex).setStartDate(new myDate(Integer.parseInt((String) yearCombo.getSelectedItem()), Integer.parseInt((String) monthCombo.getSelectedItem()),
							Integer.parseInt((String) dayCombo.getSelectedItem()),Integer.parseInt((String) hourCombo.getSelectedItem()), Integer.parseInt((String) minCombo.getSelectedItem())));
					ToDoList.myTasks.get(ToDoList.currentEditIndex).setPriority((String) priorityCombo.getSelectedItem());
					if(outdoorBtn.isSelected())
						ToDoList.myTasks.get(ToDoList.currentEditIndex).setField(true);
					else
						ToDoList.myTasks.get(ToDoList.currentEditIndex).setField(false);
					ToDoList.myTasks.get(ToDoList.currentEditIndex).setCategory((String) categoryCombo.getSelectedItem());
					ToDoList.myTasks.get(ToDoList.currentEditIndex).setNote(noteDisplay.getText());
					
					ToDoList.listModel.remove(ToDoList.currentEditIndex);
					ToDoList.listModel.add(ToDoList.currentEditIndex, ToDoList.myTasks.get(ToDoList.currentEditIndex).getName());
					ToDoList.listenerOn = true;
					ToDoList.edit1.setVisible(false);
					ToDoList.changeOutputDetails(ToDoList.currentEditIndex);
					ToDoList.exported = false;
					JOptionPane.showMessageDialog(null, "Edit task Successful.");
				}
			}
			catch(NumberFormatException n)
			{
				JOptionPane.showMessageDialog(null, "Please fill field.");
			}
		}
		if(e.getSource() == cancel)
		{
			ToDoList.listenerOn = true;
			ToDoList.edit1.setVisible(false);
		}
	}	
}
