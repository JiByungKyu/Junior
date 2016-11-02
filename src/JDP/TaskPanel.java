package JDP;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class TaskPanel extends JPanel {
	JPanel pn_taskList;
	JPanel pn_button;
	JList taskList;
	JButton btn_add;
	JButton btn_delete;
	JButton btn_showAll;
	
	TaskPanel(){
		pn_taskList = new JPanel();
		pn_button = new JPanel();
		taskList = new JList();
		btn_add = new JButton("일정 추가");
		btn_delete = new JButton("삭제");
		btn_showAll = new JButton("일정 모두 보기");
		
		setLayout(new BorderLayout());
		pn_button.setBackground(Color.WHITE);
		
		taskList.setFixedCellWidth(250);
		JScrollPane scroller = new JScrollPane(taskList);//스크롤 생성
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskList.setVisibleRowCount(10);//10줄까지만 보이도록 설정
		taskList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pn_taskList.setBackground(Color.WHITE);
		pn_taskList.add(scroller);
		
		pn_button.add(btn_add);
		pn_button.add(btn_delete);
		pn_button.add(btn_showAll);
		
		add(pn_taskList,"North");
		add(new JTextArea(1,1));
		add(pn_button,"South");
	}
}
