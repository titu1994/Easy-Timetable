package startdesign;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.SubjectData;
import easytimetable.database.TimeTable;
import easytimetable.database.TimeTableDB;

public class StudentTTView extends JFrame{
	private JTable table;
	ArrayList<SlotData> sd = SlotDB.getSlotData();

	public static int year, div;

	public StudentTTView() {


		setBackground(Color.GRAY);
		setSize(850,450);
		getContentPane().setLayout(null);

		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBounds(42, 394, 748, -336);
		getContentPane().add(table);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(372, 11, 89, 23);
		getContentPane().add(btnBack);



		table = new JTable(dataModel);
		JScrollPane scrollpane = new JScrollPane(table);


	}

	TableModel dataModel = new AbstractTableModel() {
		public int getColumnCount() { return 6; }
		public int getRowCount() { return slotSize;}
		int startTime = 8;
		int slotSize = SlotDB.getSlotCount();
		TimeTable t = TimeTableDB.getTimeTable(year, div);
		ArrayList<SlotData> slots = t.slots;

		public Object getValueAt(int row, int col) { 
			
			if(col == 5) {
				if(row == 3) {
					return "Break";
				}
				if(row == 6)
					return "Break";
				
				return "" + (startTime++);
			}
			StringBuffer sb = new StringBuffer();
			SlotData slot = slots.get(row + col*slotSize);
			if(slot.subs == null && slot.isBreak) {
				return "Break";
			}
			for(SubjectData sub : slot.subs) {
				sb.append(sub.name + ",");
			}
			String s = sb.toString().substring(0, sb.length()-1);
			return s;
		}
	};

}
