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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentPortal extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	
	public StudentPortal() {
		setSize(850, 450);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(198)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(203, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Year:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(58, 46, 112, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblChooseDivision = new JLabel("Choose Division:");
		lblChooseDivision.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseDivision.setBounds(58, 113, 112, 39);
		panel.add(lblChooseDivision);
		
		JButton btnGetTimeTable = new JButton("Get Time Table");
		btnGetTimeTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectionDivision = Integer.parseInt(textField.getText().toString());
				int selectionYear = Integer.parseInt(textField_1.getText().toString());
				StudentTimeTable.div = selectionDivision;
				StudentTimeTable.year = selectionYear;
				StudentTimeTable st = new StudentTimeTable();
				st.setVisible(true);
				setVisible(false);
			}
		});
		
		btnGetTimeTable.setBounds(155, 192, 112, 42);
		panel.add(btnGetTimeTable);
		
		textField = new JTextField();
		textField.setBounds(276, 120, 112, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(276, 53, 112, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		getContentPane().setLayout(groupLayout);
		
	}
}
