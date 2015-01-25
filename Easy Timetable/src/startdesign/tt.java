package startdesign;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import easytimetable.database.RandomDB;
import easytimetable.database.RandomData;
import easytimetable.database.SlotDB;
import easytimetable.database.SubjectDB;
import easytimetable.database.SubjectData;
import easytimetable.database.TeacherDB;

public class tt extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	public tt() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Teacher's Name:");
		lblNewLabel_2.setBounds(22, 38, 80, 14);
		panel_2.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 35, 86, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Subject1:");
		lblNewLabel_3.setBounds(22, 79, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(112, 76, 86, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject2:");
		lblSubject.setBounds(22, 121, 46, 14);
		panel_2.add(lblSubject);
		
		JLabel lblSubject_1 = new JLabel("Subject3:");
		lblSubject_1.setBounds(22, 163, 46, 14);
		panel_2.add(lblSubject_1);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(22, 205, 46, 14);
		panel_2.add(lblGrade);
		
		textField_4 = new JTextField();
		textField_4.setBounds(112, 118, 86, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(112, 160, 86, 20);
		panel_2.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(112, 202, 86, 20);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(22, 254, 80, 14);
		panel_2.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(112, 251, 86, 20);
		panel_2.add(passwordField);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_2.getText();
				String s1 = textField_3.getText();
				String s2 = textField_4.getText();
				String s3 = textField_5.getText();
				String s4 = textField_6.getText();
			}
		});
		btnNewButton.setBounds(265, 56, 89, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(265, 96, 89, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(265, 141, 89, 23);
		panel_2.add(btnNewButton_2);
		
		JPanel Constraints = new JPanel();
		tabbedPane.addTab("New tab", null, Constraints, null);
		Constraints.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Years:");
		lblNewLabel_1.setBounds(33, 21, 46, 14);
		Constraints.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Division:");
		lblNewLabel.setBounds(33, 46, 46, 14);
		Constraints.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(118, 18, 86, 20);
		Constraints.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 43, 86, 20);
		Constraints.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSlotsPerDay = new JLabel("Slots per day:");
		lblSlotsPerDay.setBounds(33, 71, 86, 14);
		Constraints.add(lblSlotsPerDay);
		
		textField_7 = new JTextField();
		textField_7.setBounds(118, 68, 86, 20);
		Constraints.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblTeachersPerSlot = new JLabel("Teachers per slot:");
		lblTeachersPerSlot.setBounds(20, 102, 109, 14);
		Constraints.add(lblTeachersPerSlot);
		
		textField_8 = new JTextField();
		textField_8.setBounds(118, 99, 86, 20);
		Constraints.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblSchoolName = new JLabel("School Name:");
		lblSchoolName.setBounds(33, 132, 79, 14);
		Constraints.add(lblSchoolName);
		
		textField_9 = new JTextField();
		textField_9.setBounds(118, 129, 86, 20);
		Constraints.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RandomData r = new RandomData();
				r.maxYear = Integer.parseInt(textField.getText());
				r.gradePerYear = Integer.parseInt(textField_1.getText());
				RandomDB.storeRandomData(r);
				
				int slot = Integer.parseInt(textField_7.getText());
				SlotDB.storeSlotCount(slot);
				
				TeacherDB.storeTeacherPerSlot(Integer.parseInt(textField_8.getText()));
				
				SlotDB.storeSchoolName(textField_9.getText());
			}
		});
		btnSubmit.setBounds(115, 186, 89, 23);
		Constraints.add(btnSubmit);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 691, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("subjects", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblSubjectName = new JLabel("Subject Name:");
		lblSubjectName.setBounds(46, 53, 91, 14);
		panel_1.add(lblSubjectName);
		
		textField_10 = new JTextField();
		textField_10.setBounds(121, 50, 86, 20);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Add Subject");
		final ArrayList<SubjectData> list = new ArrayList<>();
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectData s = new SubjectData(textField_10.getText());
				list.add(s);
				SubjectDB.storeSubjectsData(list);
			}
		});
		btnNewButton_3.setBounds(79, 78, 101, 23);
		panel_1.add(btnNewButton_3);
		getContentPane().setLayout(groupLayout);
	}
}
