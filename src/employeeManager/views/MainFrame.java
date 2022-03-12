package employeeManager.views;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;

import employeeManager.common.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -4900434678709666753L;
	private JPanel contentPane;
	private JTable tblEmployee;
	private JComboBox<String> cbbTypeMain;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnDeleteAll;
	private JButton btnReport;
	private JMenuItem mniLoad;
	private JMenuItem mniSave;
	private JMenuItem mniAbout;
	private ArrayList<Employee> eList = new ArrayList<Employee>();
	private EmployeeTableModel eTableModel = new EmployeeTableModel(eList);
	private EmployeeTableModel tempTableModel = eTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initialize();
		createEvents();
	}

	private void initialize() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/employeeManager/resources/logo_16.png")));
		setTitle("Employee Manager - Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Georgia", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFile);
		
		mniLoad = new JMenuItem("Load");
		mniLoad.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/folder_open_16.png")));
		mniLoad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFile.add(mniLoad);
		
		mniSave = new JMenuItem("Save");
		mniSave.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/save_16.png")));
		mniSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFile.add(mniSave);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnHelp);
		
		mniAbout = new JMenuItem("About");
		mniAbout.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/info_blue_16.png")));
		mniAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHelp.add(mniAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblType.setBounds(40, 20, 60, 40);
		contentPane.add(lblType);
		
		cbbTypeMain = new JComboBox<String>();
		cbbTypeMain.setFont(new Font("Georgia", Font.PLAIN, 14));
		cbbTypeMain.setModel(new DefaultComboBoxModel<String>(new String[] {"General", "Commission", "Base Plus Commission"}));
		cbbTypeMain.setBounds(100, 20, 160, 40);
		contentPane.add(cbbTypeMain);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtSearch.setBounds(340, 20, 220, 40);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/find_24.png")));
		btnSearch.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnSearch.setBounds(560, 20, 40, 40);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 90, 560, 420);
		contentPane.add(scrollPane);
		
		tblEmployee = new JTable(eTableModel);
		tblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblEmployee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEmployee.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblEmployee.getTableHeader().setForeground(Color.getHSBColor(0.7f, 1.0f, 0.5f));
		//set preferred width for columns
		tblEmployee.getColumnModel().getColumn(0).setPreferredWidth(90);
		tblEmployee.getColumnModel().getColumn(1).setPreferredWidth(90);
		tblEmployee.getColumnModel().getColumn(2).setPreferredWidth(90);
		tblEmployee.getColumnModel().getColumn(3).setPreferredWidth(60);
		for (int i = 4; i < 12; i++)
			tblEmployee.getColumnModel().getColumn(i).setPreferredWidth(100);
		scrollPane.setViewportView(tblEmployee);
		
		btnAdd = new JButton(" Add");
		btnAdd.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/add_32.png")));
		btnAdd.setFont(new Font("Georgia", Font.PLAIN, 14));
		btnAdd.setBounds(640, 90, 120, 60);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/edit_32.png")));
		btnUpdate.setFont(new Font("Georgia", Font.PLAIN, 14));
		btnUpdate.setBounds(640, 180, 120, 60);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/delete_32.png")));
		btnDelete.setFont(new Font("Georgia", Font.PLAIN, 14));
		btnDelete.setBounds(640, 270, 120, 60);
		contentPane.add(btnDelete);
		
		btnDeleteAll = new JButton("Del All");
		btnDeleteAll.setEnabled(false);
		btnDeleteAll.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/delete_all_32.png")));
		btnDeleteAll.setFont(new Font("Georgia", Font.PLAIN, 14));
		btnDeleteAll.setBounds(640, 360, 120, 60);
		contentPane.add(btnDeleteAll);
		
		btnReport = new JButton("Report");
		btnReport.setEnabled(false);
		btnReport.setIcon(new ImageIcon(MainFrame.class.getResource("/employeeManager/resources/report_32.png")));
		btnReport.setFont(new Font("Georgia", Font.PLAIN, 14));
		btnReport.setBounds(640, 450, 120, 60);
		contentPane.add(btnReport);
	}
	
	private void createEvents() {
		mniAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutFrame frmAbout = new AboutFrame();
				frmAbout.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
				frmAbout.setVisible(true);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateFrame frmAdd = new AddUpdateFrame();
				frmAdd.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
				frmAdd.setVisible(true);
				
				if (frmAdd.isCanceled) return;
				//get components
				JTextField txtId = frmAdd.getTxtId();
				JTextField txtFirstName = frmAdd.getTxtFirstName();
				JTextField txtLastName = frmAdd.getTxtLastName();
				JRadioButton rbMale = frmAdd.getRbMale();
				JSpinner spDoB = frmAdd.getSpDoB();
				JTextArea txtAddress = frmAdd.getTxtAddress();
				JTextField txtPhone = frmAdd.getTxtPhone();
				JTextField txtEmail = frmAdd.getTxtEmail();
				JTextField txtSales = frmAdd.getTxtSales();
				JTextField txtRate = frmAdd.getTxtRate();
				JTextField txtBaseSalary = frmAdd.getTxtBaseSalary();
				JComboBox<String> cbbType = frmAdd.getCbbType();
				
				//create local value to hold info from textfields
				String id = txtId.getText();
				String firstName = txtFirstName.getText();
				String lastName = txtLastName.getText();
				char gender = (rbMale.isSelected())? 'M':'F';
				Date tempDate = (Date) spDoB.getValue();
				LocalDate doB = LocalDate.ofInstant(tempDate.toInstant(), ZoneId.systemDefault());
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				String email = txtEmail.getText();
				double sales = Double.parseDouble(txtSales.getText());
				double rate = Double.parseDouble(txtRate.getText());
				String eType = (String) cbbType.getSelectedItem();
				
				//create an employee object and add to the list
				Employee employee = null;
				if (eType.equals("Commission Employee")) {
					employee = new CommissionEmployee(id, firstName, lastName, gender,
							doB, address, phone, email, sales, rate);
				} else {
					double bSalary = Double.parseDouble(txtBaseSalary.getText());
					employee = new BasePlusCommissionEmployee(id, firstName, lastName, gender,
							doB, address, phone, email, sales, rate, bSalary);
				}
				
				//add to the table list
				eTableModel.addEmployee(employee);
				
				//focus the added employee
				cbbTypeMain.setSelectedIndex(0);
				int lastIndex = eTableModel.getRowCount() - 1;
				tblEmployee.setRowSelectionInterval(lastIndex, lastIndex);
				
				//enable all buttons
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnDeleteAll.setEnabled(true);
				btnReport.setEnabled(true);
			}
		});
		
		cbbTypeMain.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//set temp = main table model
				tempTableModel = eTableModel;
				
				//clear search text field
				txtSearch.setText("");
				
				//empty list don't do anything
				if (eList.isEmpty()) return;
				
				//if General is selected & set the actual model to the table
				if (cbbTypeMain.getSelectedIndex() == 0) {
					tblEmployee.setModel(tempTableModel);
				}
				
				//if Commission is selected
				if (cbbTypeMain.getSelectedIndex() == 1) {
					//create new table model with empty list
					//use foreach to add suitable employee in eList to that table model
					//set that table model to the table
					tempTableModel = new EmployeeTableModel();
					for (Employee employee : eList) {
						if (employee instanceof CommissionEmployee &&
								!(employee instanceof BasePlusCommissionEmployee))
							tempTableModel.getEmployeeList().add(employee);
					}
					tblEmployee.setModel(tempTableModel);
				}
				
				//if Base Plus Commission is selected
				if (cbbTypeMain.getSelectedIndex() == 2) {
					tempTableModel = new EmployeeTableModel();
					for (Employee employee : eList) {
						if (employee instanceof BasePlusCommissionEmployee)
							tempTableModel.getEmployeeList().add(employee);
					}
					tblEmployee.setModel(tempTableModel);
				}
				
				//disable update and delete button because the table is filtered
				//which means the selection is gone
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the search string
				String search = txtSearch.getText();
				
				//check if the string is empty or null
				if (search.isEmpty() || search == null) {
					tblEmployee.setModel(tempTableModel);
					return;
				}
				
				//create new table model with empty list
				//use for-each to add the suitable employee in the tempTableModel's list
				EmployeeTableModel tempTableModel2 = new EmployeeTableModel();
				for (Employee employee : tempTableModel.getEmployeeList()) {
					if (employee.getFirstName().contains(search) ||
							employee.getLastName().contains(search)) {
						tempTableModel2.getEmployeeList().add(employee);
					}
					tblEmployee.setModel(tempTableModel2);
				}
				
				//disable update and delete button because the table is filtered
				//which means the selection is gone
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		
		tblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblEmployee.getSelectedRow() >= 0) {
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get employee from the sub list
				int indexInSubList = tblEmployee.getSelectedRow();
				EmployeeTableModel subModel = (EmployeeTableModel) tblEmployee.getModel();
				CommissionEmployee eSelected = (CommissionEmployee) subModel.getEmployeeList().get(indexInSubList);
				
				//find index of that employee in the main list
				int index;
				for (index = 0; index < eList.size(); index++) {
					if (eSelected == eList.get(index)) break;
				}
				
				//popup add/update frame for input
				AddUpdateFrame frmUpdate = new AddUpdateFrame();
				frmUpdate.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
				
				
				//get components from that frame
				JTextField txtId = frmUpdate.getTxtId();
				JTextField txtFirstName = frmUpdate.getTxtFirstName();
				JTextField txtLastName = frmUpdate.getTxtLastName();
				JRadioButton rbMale = frmUpdate.getRbMale();
				JRadioButton rbFemale = frmUpdate.getRbFemale();
				JSpinner spDoB = frmUpdate.getSpDoB();
				JTextArea txtAddress = frmUpdate.getTxtAddress();
				JTextField txtPhone = frmUpdate.getTxtPhone();
				JTextField txtEmail = frmUpdate.getTxtEmail();
				JTextField txtSales = frmUpdate.getTxtSales();
				JTextField txtRate = frmUpdate.getTxtRate();
				JTextField txtBaseSalary = frmUpdate.getTxtBaseSalary();
				JComboBox<String> cbbType = frmUpdate.getCbbType();
				
				//push the information from the employee to the components
				txtId.setText(eSelected.getId());
				txtFirstName.setText(eSelected.getFirstName());
				txtLastName.setText(eSelected.getLastName());
				if (eSelected.getGender() == 'M') {
					rbMale.setSelected(true);
					rbFemale.setSelected(false);
				}
				else {
					rbFemale.setSelected(true);
					rbMale.setSelected(false);
				}
				Date tempDate = Date.from(eSelected.getDoB().atStartOfDay(ZoneId.systemDefault()).toInstant());
				spDoB.setValue(tempDate);
				txtAddress.setText(eSelected.getAddress());
				txtPhone.setText(eSelected.getPhone());
				txtEmail.setText(eSelected.getEmail());
				txtSales.setText(String.valueOf(eSelected.getTotalSales()));
				txtRate.setText(String.valueOf(eSelected.getCommissionRate()));
				if (eSelected instanceof BasePlusCommissionEmployee) {
					BasePlusCommissionEmployee temp = (BasePlusCommissionEmployee) eSelected;
					cbbType.setSelectedIndex(1);
					txtBaseSalary.setText(String.valueOf(temp.getBaseSalary()));
				}
				
				//show the update frame
				frmUpdate.setVisible(true);
				
				//if the user click "x" or cancel
				if (frmUpdate.isCanceled) return;
				
				//create local value to hold info from textfields
				String id = txtId.getText();
				String firstName = txtFirstName.getText();
				String lastName = txtLastName.getText();
				char gender = (rbMale.isSelected())? 'M':'F';
				tempDate = (Date) spDoB.getValue();
				LocalDate doB = LocalDate.ofInstant(tempDate.toInstant(), ZoneId.systemDefault());
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				String email = txtEmail.getText();
				double sales = Double.parseDouble(txtSales.getText());
				double rate = Double.parseDouble(txtRate.getText());
				String eType = (String) cbbType.getSelectedItem();
				
				//create an employee object and add to the list
				Employee employee = null;
				if (eType.equals("Commission Employee")) {
					employee = new CommissionEmployee(id, firstName, lastName, gender,
							doB, address, phone, email, sales, rate);
				} else {
					double bSalary = Double.parseDouble(txtBaseSalary.getText());
					employee = new BasePlusCommissionEmployee(id, firstName, lastName, gender,
							doB, address, phone, email, sales, rate, bSalary);
				}
				
				//update employee to the main table model
				eTableModel.updateEmployee(employee, index);
				
				//focus the updated employee
				cbbTypeMain.setSelectedIndex(0);
				tblEmployee.setRowSelectionInterval(index, index);
				
				//enable update and delete button
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//confirm delete
				int i = JOptionPane.showConfirmDialog(null, "Delete this employee from list?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i != JOptionPane.YES_OPTION) return; 
				
				//get employee from the sub list
				int indexInSubList = tblEmployee.getSelectedRow();
				EmployeeTableModel subModel = (EmployeeTableModel) tblEmployee.getModel();
				CommissionEmployee eSelected = (CommissionEmployee) subModel.getEmployeeList().get(indexInSubList);
				
				//find index of that employee in the main list
				int index;
				for (index = 0; index < eList.size(); index++) {
					if (eSelected == eList.get(index)) break;
				}
				
				//remove employee
				eTableModel.removeEmployee(index);
				
				//back to the general selection
				cbbTypeMain.setSelectedIndex(0);
				
				//set update and delete button to disabled
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				
				//if list is empty, disable delete all button
				if (eList.isEmpty())
					btnDeleteAll.setEnabled(false);
			}
		});
		
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check for empty list
				if (eList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "The list is already empty!", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				//confirm delete
				int i = JOptionPane.showConfirmDialog(null, "Delete all employees from list?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i != JOptionPane.YES_OPTION) return; 
				
				//proceed to delete
				eTableModel.removeAllEmployee();
				
				//set update, delete and report button to disabled
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				btnReport.setEnabled(false);
				
				//back to the general selection
				cbbTypeMain.setSelectedIndex(0);
			}
		});
		
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportFrame frmReport = new ReportFrame();
				frmReport.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
				
				//calculate info
				int employeeNum = eList.size();
				double totalSales = 0, totalCommission = 0, totalEarnings = 0;
				for (Employee employee : eList) {
					CommissionEmployee eTmp = (CommissionEmployee) employee;
					totalSales += eTmp.getTotalSales();
					totalCommission += (eTmp.getTotalSales() * eTmp.getCommissionRate());
					totalEarnings += eTmp.earnings();
				}
				
				//pass info to the components of report frame
				frmReport.getTxtEmployeeNum().setText(String.valueOf(employeeNum));
				frmReport.getTxtTotalSales().setText(String.format("%,.1f", totalSales));
				frmReport.getTxtTotalCommission().setText(String.format("%,.1f", totalCommission));
				frmReport.getTxtTotalEarnings().setText(String.format("%,.1f", totalEarnings));
				
				//show the report frame
				frmReport.setVisible(true);
			}
		});
		
		mniSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Create filechooser, set default directory and only show .dat file
					JFileChooser fileDialog = new JFileChooser();
					fileDialog.setCurrentDirectory(new File("./"));
					fileDialog.addChoosableFileFilter(new DatabaseFilter());
					fileDialog.setAcceptAllFileFilterUsed(false);
					
					//get file
					int returnVal = fileDialog.showSaveDialog(null);
					File file;
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fileDialog.getSelectedFile();
					} else return;
					
					//check regexp
					String fileName = file.getName();
					if (!fileName.matches("[a-zA-Z0-9]+\\.dat")) {
						if (fileName.matches("[a-zA-Z0-9]+")) {
							file = new File(file + ".dat");
						}
						else throw new Exception("The file name is illegal!");
					}
					
					//call helper to write the list to a file
					SaveLoadHelper helper = new SaveLoadHelper(file, SaveLoadHelper.OUTPUT_MODE);
					helper.write(eList);
					
					//notify the user
					JOptionPane.showMessageDialog(null, "The employee list has been saved as \"" +
								file.getName() + "\"!");
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null,
							"Error happening when saving the file. It may be because you have\n" +
							"a folder with the same name or creating permission is restricted.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		mniLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Create filechooser, set default directory and only show .dat file
					JFileChooser fileDialog = new JFileChooser();
					fileDialog.setCurrentDirectory(new File("./"));
					fileDialog.addChoosableFileFilter(new DatabaseFilter());
					fileDialog.setAcceptAllFileFilterUsed(false);
					
					//get file
					int returnVal = fileDialog.showOpenDialog(null);
					File file;
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fileDialog.getSelectedFile();
					} else return;
					
					//call helper to load the file
					SaveLoadHelper helper = new SaveLoadHelper(file, SaveLoadHelper.INPUT_MODE);
					eList = helper.load();
					if (eList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "The loaded list is empty!");
						return;
					}
					eTableModel = new EmployeeTableModel(eList);
					tempTableModel = eTableModel;
					tblEmployee.setModel(eTableModel);
					
					btnDeleteAll.setEnabled(true);
					btnReport.setEnabled(true);
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null,
							"Error happening when loading the file. It may be because the chosen\n" +
							"file is actually a folder or reading permission is restricted.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (StreamCorruptedException ex) {
					JOptionPane.showMessageDialog(null,
							"Incorrect stream header. Your choosen file is probably not\n" +
							"the data file needed for this program.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}

	public JButton getBtnDeleteAll() {
		return btnDeleteAll;
	}

	public JButton getBtnReport() {
		return btnReport;
	}
	
	public JTable getTblEmployee() {
		return tblEmployee;
	}
	
	public EmployeeTableModel geteTableModel() {
		return eTableModel;
	}

	public void seteList(ArrayList<Employee> eList) {
		this.eList = eList;
	}

	public void seteTableModel(EmployeeTableModel eTableModel) {
		this.eTableModel = eTableModel;
	}

	public void setTempTableModel(EmployeeTableModel tempTableModel) {
		this.tempTableModel = tempTableModel;
	}
	
}
