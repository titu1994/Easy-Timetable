package startdesign;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.SubjectData;
import easytimetable.database.TeacherDB;
import easytimetable.database.TeacherData;
import easytimetable.database.TimeTable;
import easytimetable.database.TimeTableDB;

public class TeacherTTView extends JFrame {
	private JTable table;
	public static int year;
	ArrayList<SlotData> sd = SlotDB.getSlotData();
	public static int id;

	public TeacherTTView() {
		setSize(850, 450);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 834, 426);
		lblNewLabel.setBackground(Color.WHITE);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(312, 11, 200, 50);
		getContentPane().add(btnNewButton);

		table = new JTable();
		table.setBounds(55, 110, 734, 291);
		getContentPane().add(table);

	}

	TableModel dataModel = new AbstractTableModel() {
		TimeTable t;
		ArrayList<SlotData> slots;
		TeacherData teacher;
		public int getColumnCount() {
			return 6;
		}

		public int getRowCount() {
			int div = 1;
			t = TimeTableDB.getTimeTable(year, div);
			slots = t.slots;
			teacher = TeacherDB.getTeacherData(id);
			
			return slotSize;
		}

		int slotSize = SlotDB.getSlotCount();

		int startTime = 8;

		public Object getValueAt(int row, int col) {
			StringBuffer sb = new StringBuffer();
			SlotData slot = slots.get(row + col * slotSize);
			if (col == 5) {
				if (row == 3) {
					return "Break";
				}
				if (row == 6)
					return "Break";

				return "" + (startTime++);
			}
			if (slot.subs == null && slot.isBreak) {
				return "Break";
			}
			
			SubjectData subs[] = teacher.subjects;
			
			for (SubjectData sub : subs) {
				for(SubjectData subb : slot.subs) {
					if(sub.name.equals(subb.name)) {
						return sub.name;
					}
				}
			}
			return null;
		}
	};
}
