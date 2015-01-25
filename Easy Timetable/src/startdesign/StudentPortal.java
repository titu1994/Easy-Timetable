package startdesign;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import easytimetable.database.RandomDB;
import easytimetable.database.RandomData;
import easytimetable.database.TimeTable;

import javax.swing.JButton;
import javax.swing.JTextField;

public class StudentPortal extends JFrame{
	private JTextField textField;
	
	
	public static void main(String args[]) {
		StudentPortal portal = new StudentPortal();
		portal.setVisible(true);
	}
	
	public StudentPortal() {
		setSize(850, 450);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(198)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Year:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(42, 42, 112, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblChooseDivision = new JLabel("Choose Division:");
		lblChooseDivision.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseDivision.setBounds(42, 109, 112, 39);
		panel.add(lblChooseDivision);
		
		RandomData r = RandomDB.getRandomData();
		int x = r.maxYear;
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(276, 53, 112, 20);
		for(int i = 1; i < x; i++)
			comboBox.addItem(""+x);
		panel.add(comboBox);
		
		
		JButton btnGetTimeTable = new JButton("Get Time Table");
		btnGetTimeTable.setBounds(155, 192, 112, 42);
		panel.add(btnGetTimeTable);
		
		textField = new JTextField();
		textField.setBounds(276, 120, 112, 20);
		panel.add(textField);
		textField.setColumns(10);
		getContentPane().setLayout(groupLayout);
		
		int selectionYear = Integer.parseInt(comboBox.getSelectedItem().toString());
		int selectionDivision = Integer.parseInt(textField.getText().toString());
		
		StudentTTView.div = selectionDivision;
		StudentTTView.year = selectionYear;
	}
}
