package startdesign;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import easytimetable.database.RandomDB;
import easytimetable.database.RandomData;
import easytimetable.database.SlotDB;
import easytimetable.database.SubjectDB;
import easytimetable.database.SubjectData;
import easytimetable.database.TeacherDB;
import easytimetable.database.TeacherData;
import easytimetable.logic.AdminClass;
import easytimetable.logic.AdminClass.AdminInterface;

public class TimeTableUI extends JFrame{
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
	private JTextField textField_11;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JCheckBox checkBox_4;
	private JCheckBox checkBox_5;
	private JCheckBox checkBox_6;
	private JCheckBox checkBox_7;
	private JCheckBox checkBox_8;
	private JCheckBox checkBox_9;
	private JCheckBox checkBox_10;
	private JCheckBox checkBox_11;
	private JCheckBox checkBox_12;
	private JCheckBox checkBox_13;
	private JCheckBox checkBox_14;
	private JCheckBox checkBox_15;
	private JCheckBox checkBox_16;
	private JCheckBox checkBox_17;
	private JCheckBox checkBox_18;
	private JCheckBox checkBox_19;
	private JCheckBox checkBox_20;
	private JCheckBox checkBox_21;
	private JCheckBox checkBox_22;
	private JCheckBox checkBox_23;
	private JCheckBox checkBox_24;

	private JCheckBox checkBoxArray[];
	private JTextField textField_12;
	
	private AdminClass admin;
	private JTextField textField_13;
	private JPasswordField passwordField_1;
	private JLabel lblDivision;
	private JTextField textField_14;
	
	private AdminInterface adminInteface = new AdminInterface() {
		
		@Override
		public void notifyTeacherUnavailable() {
			JOptionPane.showMessageDialog(tabbedPane, "Insufficient teachers available to create timetable.");
		}
		
		@Override
		public void notifyAdmin(boolean isValid) {
			System.out.println("Admin is : " + isValid);
		}
	};
	private JTabbedPane tabbedPane;
	private JCheckBox chckbxIsAvailable;

	public TimeTableUI() {
		admin = new AdminClass();
		admin.setInterf(adminInteface);
		
		setSize(850, 450);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel Teacher = new JPanel();
		tabbedPane.addTab("Teacher", null, Teacher, null);
		Teacher.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Teacher's Name:");
		lblNewLabel_2.setBounds(22, 38, 80, 14);
		Teacher.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(112, 35, 86, 20);
		Teacher.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Subject1:");
		lblNewLabel_3.setBounds(22, 79, 46, 14);
		Teacher.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(112, 76, 86, 20);
		Teacher.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblSubject = new JLabel("Subject2:");
		lblSubject.setBounds(22, 121, 46, 14);
		Teacher.add(lblSubject);

		JLabel lblSubject_1 = new JLabel("Subject3:");
		lblSubject_1.setBounds(22, 163, 46, 14);
		Teacher.add(lblSubject_1);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(22, 205, 46, 14);
		Teacher.add(lblGrade);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(0, 0, 6, 20);
		Teacher.add(formattedTextField);

		textField_4 = new JTextField();
		textField_4.setBounds(112, 118, 86, 20);
		Teacher.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(112, 160, 86, 20);
		Teacher.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(112, 202, 86, 20);
		Teacher.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(22, 254, 80, 14);
		Teacher.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(112, 251, 86, 20);
		Teacher.add(passwordField);

		JButton btnNewButton = new JButton("Add");

		final ArrayList<TeacherData> list = new ArrayList<>();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_2.getText();
				String s1 = textField_3.getText();
				String s2 = textField_4.getText();
				String s3 = textField_5.getText();
				int grade = Integer.parseInt(textField_6.getText());
				String arr[] = {s1, s2, s3};
				String pass = new String(passwordField.getPassword());
				int count = 0;
				if(!s1.equals(""))
					count++;
				if(!s2.equals(""))
					count++;

				if(!s3.equals(""))
					count++;


				SubjectData sub[] = new SubjectData[count];
				for(int i = 0; i < count; i++) {
					if(!arr[i].equals(""))
						sub[i] = new SubjectData(arr[i]);
				}
				
				boolean isAvailable = chckbxIsAvailable.isSelected();
				TeacherData t = new TeacherData(name, sub, pass, grade);
				t.isAvailable = isAvailable;
				list.add(t);
				TeacherDB.addTeacher(t);

			}
		});
		btnNewButton.setBounds(265, 56, 89, 23);
		Teacher.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_2.getText();
				String s1 = textField_3.getText();
				String s2 = textField_4.getText();
				String s3 = textField_5.getText();
				int grade = Integer.parseInt(textField_6.getText());
				String arr[] = {s1, s2, s3};
				String pass = new String(passwordField.getPassword());
				int count = 0;
				if(!s1.equals(""))
					count++;
				if(!s2.equals(""))
					count++;
				if(!s3.equals(""))
					count++;


				SubjectData sub[] = new SubjectData[count];
				for(int i = 0; i < count; i++) {
					if(!arr[i].equals(""))
						sub[i] = new SubjectData(arr[i]);
				}
				
				boolean isAvailable = chckbxIsAvailable.isSelected();
				TeacherData t = new TeacherData(name, sub, pass, grade);
				t.isAvailable = isAvailable;
				list.add(t);
				TeacherDB.updateTeacher(t);
			}
		});
		btnNewButton_1.setBounds(265, 96, 89, 23);
		Teacher.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton_2.setBounds(265, 141, 89, 23);
		Teacher.add(btnNewButton_2);
		
		chckbxIsAvailable = new JCheckBox("Is Available");
		chckbxIsAvailable.setSelected(true);
		chckbxIsAvailable.setBounds(45, 299, 97, 23);
		Teacher.add(chckbxIsAvailable);

		JPanel Constraints = new JPanel();
		tabbedPane.addTab("Constraints", null, Constraints, null);
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
		
		JLabel lblBreakTimeswith = new JLabel("Break Times (with comma)");
		lblBreakTimeswith.setBounds(10, 167, 124, 14);
		Constraints.add(lblBreakTimeswith);
		
		textField_12 = new JTextField();
		textField_12.setBounds(144, 164, 86, 20);
		Constraints.add(textField_12);
		textField_12.setColumns(10);


		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] timeBreaks = textField_12.getText().split(",");
				int times[] = new int[timeBreaks.length];
				for(int i = 0; i < times.length; i++) {
					times[i] = Integer.parseInt(timeBreaks[i]) - 1;
				}
				
				Arrays.sort(times);
				
				RandomData r = new RandomData();
				r.maxYear = Integer.parseInt(textField.getText());
				r.gradePerYear = Integer.parseInt(textField_1.getText());
				r.breakTimes = times;
				RandomDB.storeRandomData(r);

				int slot = Integer.parseInt(textField_7.getText());
				SlotDB.storeSlotCount(slot);

				TeacherDB.storeTeacherPerSlot(Integer.parseInt(textField_8.getText()));

				SlotDB.storeSchoolName(textField_9.getText());
			}
		});
		btnSubmit.setBounds(115, 217, 89, 23);
		Constraints.add(btnSubmit);
		
		
		JPanel Subjects = new JPanel();
		tabbedPane.addTab("Subjects", null, Subjects, null);
		Subjects.setLayout(null);

		JLabel lblSubjectName = new JLabel("Subject Name:");
		lblSubjectName.setBounds(46, 53, 91, 14);
		Subjects.add(lblSubjectName);

		textField_10 = new JTextField();
		textField_10.setBounds(121, 50, 86, 28);
		Subjects.add(textField_10);
		textField_10.setColumns(10);

		JButton btnNewButton_3 = new JButton("Add Subject");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectData s = new SubjectData(textField_10.getText());
				SubjectDB.addSubject(s);
				textField_10.setText("");
			}
		});
		btnNewButton_3.setBounds(80, 94, 101, 23);
		Subjects.add(btnNewButton_3);
		
		JPanel Credentials = new JPanel();
		tabbedPane.addTab("Credentials", null, Credentials, null);
		Credentials.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Admin Username");
		lblNewLabel_4.setBounds(50, 46, 80, 14);
		Credentials.add(lblNewLabel_4);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setBounds(50, 83, 78, 14);
		Credentials.add(lblAdminPassword);
		
		textField_13 = new JTextField();
		textField_13.setBounds(153, 43, 86, 20);
		Credentials.add(textField_13);
		textField_13.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(153, 80, 86, 20);
		Credentials.add(passwordField_1);
		
		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_13.getText();
				String pass = new String(passwordField_1.getPassword());
				admin.storeAdminCredentials(name, pass);
			}
		});
		btnSubmit_1.setBounds(100, 133, 89, 23);
		Credentials.add(btnSubmit_1);

		JPanel Timetable = new JPanel();
		tabbedPane.addTab("Timetable", null, Timetable, null);
		Timetable.setLayout(null);


		textField_11 = new JTextField();
		textField_11.setBounds(590, 68, 86, 20);
		Timetable.add(textField_11);
		textField_11.setColumns(10);

		JButton btnNewButton_4 = new JButton("Create Timetable");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(textField_11.getText());
				RandomData randomData = RandomDB.getRandomData();
				int breakList[] = randomData.breakTimes;
				admin.setBreakList(breakList);
				admin.setDivisin(Integer.parseInt(textField_14.getText()));
				admin.setYear(year);
				
				ArrayList<SubjectData> subjects = getCheckedSubjects();
				admin.setSubjectList(subjects);
				
				admin.setMaxNoOfTeachersInClassRoom(TeacherDB.getTeacherPerSlot());
				admin.setSlotCount(SlotDB.getSlotCount());
				
				admin.createTimeTable();
			}
		});
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("691px"),},
				new RowSpec[] {
				RowSpec.decode("430px"),}));
		btnNewButton_4.setBounds(524, 142, 115, 23);
		Timetable.add(btnNewButton_4);

		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(488, 71, 46, 14);
		Timetable.add(lblYear);

		checkBox = new JCheckBox("");
		checkBox.setEnabled(false);
		checkBox.setBounds(30, 29, 97, 23);
		Timetable.add(checkBox);

		checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(false);
		checkBox_1.setBounds(30, 67, 97, 23);
		Timetable.add(checkBox_1);

		checkBox_2 = new JCheckBox("");
		checkBox_2.setEnabled(false);
		checkBox_2.setBounds(30, 106, 97, 23);
		Timetable.add(checkBox_2);

		checkBox_3 = new JCheckBox("");
		checkBox_3.setEnabled(false);
		checkBox_3.setBounds(30, 142, 97, 23);
		Timetable.add(checkBox_3);

		checkBox_4 = new JCheckBox("");
		checkBox_4.setEnabled(false);
		checkBox_4.setBounds(30, 174, 97, 23);
		Timetable.add(checkBox_4);

		checkBox_5 = new JCheckBox("");
		checkBox_5.setEnabled(false);
		checkBox_5.setBounds(30, 205, 97, 23);
		Timetable.add(checkBox_5);

		checkBox_6 = new JCheckBox("");
		checkBox_6.setEnabled(false);
		checkBox_6.setBounds(30, 237, 97, 23);
		Timetable.add(checkBox_6);

		checkBox_7 = new JCheckBox("");
		checkBox_7.setEnabled(false);
		checkBox_7.setBounds(30, 270, 97, 23);
		Timetable.add(checkBox_7);

		checkBox_8 = new JCheckBox("");
		checkBox_8.setEnabled(false);
		checkBox_8.setBounds(30, 305, 97, 23);
		Timetable.add(checkBox_8);

		checkBox_9 = new JCheckBox("");
		checkBox_9.setEnabled(false);
		checkBox_9.setBounds(30, 340, 97, 23);
		Timetable.add(checkBox_9);

		checkBox_10 = new JCheckBox("");
		checkBox_10.setEnabled(false);
		checkBox_10.setBounds(162, 29, 97, 23);
		Timetable.add(checkBox_10);

		checkBox_11 = new JCheckBox("");
		checkBox_11.setEnabled(false);
		checkBox_11.setBounds(162, 67, 97, 23);
		Timetable.add(checkBox_11);

		checkBox_12 = new JCheckBox("");
		checkBox_12.setEnabled(false);
		checkBox_12.setBounds(162, 106, 97, 23);
		Timetable.add(checkBox_12);

		checkBox_13 = new JCheckBox("");
		checkBox_13.setEnabled(false);
		checkBox_13.setBounds(162, 142, 97, 23);
		Timetable.add(checkBox_13);

		checkBox_14 = new JCheckBox("");
		checkBox_14.setEnabled(false);
		checkBox_14.setBounds(162, 174, 97, 23);
		Timetable.add(checkBox_14);

		checkBox_15 = new JCheckBox("");
		checkBox_15.setEnabled(false);
		checkBox_15.setBounds(162, 205, 97, 23);
		Timetable.add(checkBox_15);

		checkBox_16 = new JCheckBox("");
		checkBox_16.setEnabled(false);
		checkBox_16.setBounds(162, 237, 97, 23);
		Timetable.add(checkBox_16);

		checkBox_17 = new JCheckBox("");
		checkBox_17.setEnabled(false);
		checkBox_17.setBounds(162, 270, 97, 23);
		Timetable.add(checkBox_17);

		checkBox_18 = new JCheckBox("");
		checkBox_18.setEnabled(false);
		checkBox_18.setBounds(162, 305, 97, 23);
		Timetable.add(checkBox_18);

		checkBox_19 = new JCheckBox("");
		checkBox_19.setEnabled(false);
		checkBox_19.setBounds(162, 340, 97, 23);
		Timetable.add(checkBox_19);

		checkBox_20 = new JCheckBox("");
		checkBox_20.setEnabled(false);
		checkBox_20.setBounds(288, 29, 97, 23);
		Timetable.add(checkBox_20);

		checkBox_21 = new JCheckBox("");
		checkBox_21.setEnabled(false);
		checkBox_21.setBounds(288, 67, 97, 23);
		Timetable.add(checkBox_21);

		checkBox_22 = new JCheckBox("");
		checkBox_22.setEnabled(false);
		checkBox_22.setBounds(288, 106, 97, 23);
		Timetable.add(checkBox_22);

		checkBox_23 = new JCheckBox("");
		checkBox_23.setEnabled(false);
		checkBox_23.setBounds(288, 142, 97, 23);
		Timetable.add(checkBox_23);

		checkBox_24 = new JCheckBox("");
		checkBox_24.setEnabled(false);
		checkBox_24.setBounds(288, 174, 97, 23);
		Timetable.add(checkBox_24);
		
		lblDivision = new JLabel("Division:");
		lblDivision.setBounds(488, 96, 40, 14);
		Timetable.add(lblDivision);
		
		textField_14 = new JTextField();
		textField_14.setBounds(590, 93, 86, 20);
		Timetable.add(textField_14);
		textField_14.setColumns(10);

		checkBoxArray = new JCheckBox[]{checkBox, checkBox_1, checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6, checkBox_7, checkBox_8, checkBox_9, checkBox_10,
				checkBox_11, checkBox_12, checkBox_13, checkBox_14, checkBox_15, checkBox_16, checkBox_17, checkBox_18, checkBox_19, checkBox_20,
				checkBox_21, checkBox_22, checkBox_23, checkBox_24};

		initCheckBoxes();
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				System.out.println(index);
				if(index == 3) {
					initCheckBoxes();
				}
			}
		});

		getContentPane().add(tabbedPane, "1, 1, fill, fill");
	}

	protected ArrayList<SubjectData> getCheckedSubjects() {
		ArrayList<SubjectData> subs = new ArrayList<>();
		for(JCheckBox check : checkBoxArray) {
			if(check.isVisible() && check.isSelected() && check.isEnabled()) {
				subs.add(new SubjectData(check.getText()));
			}
		}
		
		return subs;
	}

	private void initCheckBoxes() {
		ArrayList<SubjectData> allSubList = SubjectDB.getSubjectData();
		for(int i = 0; i < allSubList.size(); i++) {
			checkBoxArray[i].setText(allSubList.get(i).name);
			checkBoxArray[i].setEnabled(true);
			checkBoxArray[i].setVisible(true);
		}

		for(int i = allSubList.size(); i < checkBoxArray.length; i++) {
			checkBoxArray[i].setVisible(false);
		}
	}
}
