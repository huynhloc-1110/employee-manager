package employeeManager.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;

import java.util.ArrayList;

import employeeManager.common.*;

public class StartFrame {

	private JFrame frmStart;
	private JButton btnNew;
	private JButton btnOpen;

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
					StartFrame window = new StartFrame();
					window.frmStart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartFrame() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStart = new JFrame();
		frmStart.setIconImage(Toolkit.getDefaultToolkit().getImage(StartFrame.class.getResource("/employeeManager/resources/logo_16.png")));
		frmStart.setResizable(false);
		frmStart.setTitle("Employee Manager - Start");
		frmStart.setBounds(0, 0, 560, 300);
		frmStart.setLocationRelativeTo(null);
		frmStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStart.getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(StartFrame.class.getResource("/employeeManager/resources/logo_128.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Georgia", Font.PLAIN, 30));
		lblLogo.setBounds(50, 10, 200, 200);
		frmStart.getContentPane().add(lblLogo);
		
		JLabel lblProgramName = new JLabel("Employee Manager");
		lblProgramName.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramName.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 22));
		lblProgramName.setBounds(35, 180, 230, 30);
		frmStart.getContentPane().add(lblProgramName);
		
		JLabel lblGetStarted = new JLabel("Get Started");
		lblGetStarted.setHorizontalAlignment(SwingConstants.CENTER);
		lblGetStarted.setFont(new Font("Georgia", Font.BOLD, 16));
		lblGetStarted.setBounds(350, 50, 120, 60);
		frmStart.getContentPane().add(lblGetStarted);
		
		btnNew = new JButton("Create a new database");
		btnNew.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnNew.setBounds(310, 110, 200, 40);
		frmStart.getContentPane().add(btnNew);
		
		btnOpen = new JButton("Open a local database");
		btnOpen.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnOpen.setBounds(310, 170, 200, 40);
		frmStart.getContentPane().add(btnOpen);
	}
	
	private void createEvents() {
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame frmMain = new MainFrame();
				frmStart.setVisible(false);
				frmMain.setVisible(true);
			}
		});
		
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame frmMain = new MainFrame();
					
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
					
					//get components from the main frame
					JTable tblEmployee = frmMain.getTblEmployee();
					JButton btnDeleteAll = frmMain.getBtnDeleteAll();
					JButton btnReport = frmMain.getBtnReport();
					
					//call helper to load the file
					SaveLoadHelper helper = new SaveLoadHelper(file, SaveLoadHelper.INPUT_MODE);
					ArrayList<Employee> tempList = helper.load();
					if (tempList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "The loaded list is empty!");
						return;
					}
					
					//setup components in the main frame
					frmMain.seteList(tempList);
					frmMain.seteTableModel(new EmployeeTableModel(tempList));
					frmMain.setTempTableModel(frmMain.geteTableModel());
					tblEmployee.setModel(frmMain.geteTableModel());
					btnDeleteAll.setEnabled(true);
					btnReport.setEnabled(true);
					
					frmMain.setVisible(true);
					frmStart.setVisible(false);
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
}
