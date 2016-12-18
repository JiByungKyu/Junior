package JDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CalendarFrame extends JFrame {
	Calendar calendar;
	JPanel pn_calendar;
	JLabel label;
	JTable tb_dateView;
	JTable tb_weekLabel;

	DefaultTableModel dtm_weekTable;
	DefaultTableModel dtm_dateTable;

	String[] weekColumn = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

	public CalendarFrame() {
		calendar = Calendar.getInstance();
		label = new JLabel();

		dtm_weekTable = new DefaultTableModel(0, 7);
		dtm_dateTable = new DefaultTableModel(6, 7);

		pn_calendar = new JPanel();
		pn_calendar.setLayout(new BorderLayout());
		pn_calendar.setBackground(Color.white);
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(70, 10, 384, 54);
		pn_calendar.add(label);

		pn_calendar.add(label, BorderLayout.NORTH);
		tb_dateView = new JTable(dtm_dateTable) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};

		};
		setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer sunRed = new DefaultTableCellRenderer();
		sunRed.setForeground(Color.RED);
		sunRed.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer satBlue = new DefaultTableCellRenderer();
		satBlue.setForeground(Color.BLUE);
		satBlue.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer sunDateRed = new DefaultTableCellRenderer();
		sunDateRed.setForeground(Color.RED);
		sunDateRed.setVerticalAlignment(SwingConstants.TOP);
		DefaultTableCellRenderer satDateBlue = new DefaultTableCellRenderer();
		satDateBlue.setForeground(Color.BLUE);
		satDateBlue.setVerticalAlignment(SwingConstants.TOP);
		DefaultTableCellRenderer top = new DefaultTableCellRenderer();
		top.setVerticalAlignment(SwingConstants.TOP);

		dtm_weekTable.addRow(weekColumn);

		tb_weekLabel = new JTable(dtm_weekTable);
		tb_weekLabel.setEnabled(false);
		tb_weekLabel.setRowSelectionAllowed(false);
		tb_weekLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tb_weekLabel.setBackground(Color.WHITE);
		tb_weekLabel.setRowHeight(25);
		tb_weekLabel.setFillsViewportHeight(true);
		tb_weekLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tb_weekLabel.setBounds(12, 85, 500, 23);

		tb_weekLabel.getColumnModel().getColumn(1).setCellRenderer(center);
		tb_weekLabel.getColumnModel().getColumn(2).setCellRenderer(center);
		tb_weekLabel.getColumnModel().getColumn(3).setCellRenderer(center);
		tb_weekLabel.getColumnModel().getColumn(4).setCellRenderer(center);
		tb_weekLabel.getColumnModel().getColumn(5).setCellRenderer(center);
		tb_weekLabel.getColumnModel().getColumn(0).setCellRenderer(sunRed);
		tb_weekLabel.getColumnModel().getColumn(6).setCellRenderer(satBlue);
		pn_calendar.add(tb_weekLabel, BorderLayout.CENTER);

		tb_dateView.setCellSelectionEnabled(true);
		tb_dateView.setColumnSelectionAllowed(true);
		tb_dateView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// do nothing yet
			}
		});
		tb_dateView.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tb_dateView.setBorder(new LineBorder(new Color(0, 0, 0)));
		tb_dateView.setRowHeight(60);
		tb_dateView.setFillsViewportHeight(true);
		tb_dateView.setBounds(12, 107, 500, 360);
		tb_dateView.getColumnModel().getColumn(0).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(1).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(2).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(3).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(4).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(5).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(6).setCellRenderer(top);
		tb_dateView.getColumnModel().getColumn(0).setCellRenderer(sunDateRed);
		tb_dateView.getColumnModel().getColumn(6).setCellRenderer(satDateBlue);
		pn_calendar.add(tb_dateView, BorderLayout.SOUTH);

		update();
	}

	protected void update() {

		label.setText((calendar.get(Calendar.YEAR) + "년, ") + (calendar.get(Calendar.MONTH) + 1) + "월");
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				tb_dateView.setValueAt("", i, j);
			}
		}
		for (int day = 1, row = 0, col = dayWeek - 1; day < endDay + 1; day++, col++) {
			if (col % 7 == 0) {
				col = 0;
				row += 1;
			}
			tb_dateView.setValueAt(" " + day, row, col);
		}
	}
}
