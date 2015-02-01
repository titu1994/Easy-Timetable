package startdesign;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import easytimetable.database.RandomDB;
import easytimetable.database.RandomData;
import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.SubjectData;
import easytimetable.database.TimeTable;
import easytimetable.database.TimeTableDB;

public class StudentTimeTable extends JFrame{
	private JTable table;
	public static int year, div;
	
	public StudentTimeTable() {
		setSize(850, 500);
		
		System.out.println("Student Time Table");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		TableModel dataModel = new AbstractTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2677438971449337740L;
			
			RandomData r = RandomDB.getRandomData();
			private int breakCount = 0;

			public int getColumnCount() { return 5; }
			public int getRowCount() { return slotSize + 1;}
			int startTime = 8;
			int slotSize = SlotDB.getSlotCount();
			TimeTable t = TimeTableDB.getTimeTable(year, div);
			ArrayList<SlotData> slots = t.slots;

			public Object getValueAt(int row, int col) { 			
				if(row == 0) {
					if(col == 0) return "Monday";
					if(col == 1) return "Tuesday";
					if(col == 2) return "Wednesday";
					if(col == 3) return "Thursday";
					if(col == 4) return "Friday";
				}
				else {
					row--;
				}
				
				/*if(col == 5) {
					if(r.breakTimes[breakCount] == row)
						return "Break";
					
					return "" + (startTime++);
				}*/
				
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
			
			@Override
			public String getColumnName(int c) {
				if(c == 0) return "Monday";
				if(c == 1) return "Tuesday";
				if(c == 2) return "Wednesday";
				if(c == 3) return "Thursday";
				if(c == 4) return "Friday";
				return "Null";
			}
		};
		
		table = new JTable(dataModel);
		getContentPane().add(table);
		table.setBounds(10, 449, 824, -376);
		table.setVisible(true);
		
	}
	
	
}
