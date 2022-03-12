package employeeManager.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import employeeManager.common.*;

public class ReportFrame extends JDialog {

	private static final long serialVersionUID = -5395940629464604280L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmployeeNum;
	private JTextField txtTotalSales;
	private JTextField txtTotalCommission;
	private JTextField txtTotalEarnings;

	//getters
	public JTextField getTxtEmployeeNum() {
		return txtEmployeeNum;
	}

	public JTextField getTxtTotalSales() {
		return txtTotalSales;
	}

	public JTextField getTxtTotalCommission() {
		return txtTotalCommission;
	}

	public JTextField getTxtTotalEarnings() {
		return txtTotalEarnings;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReportFrame dialog = new ReportFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReportFrame() {
		setBounds(0, 0, 440, 320);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("REPORT");
		lblTitle.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(165, 20, 100, 30);
		contentPanel.add(lblTitle);
		
		JLabel lblEmployeeNumber = new JLabel("Employee Number:");
		lblEmployeeNumber.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblEmployeeNumber.setBounds(25, 60, 130, 30);
		contentPanel.add(lblEmployeeNumber);
		
		txtEmployeeNum = new JTextField();
		txtEmployeeNum.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtEmployeeNum.setEditable(false);
		txtEmployeeNum.setBounds(155, 60, 250, 30);
		contentPanel.add(txtEmployeeNum);
		txtEmployeeNum.setColumns(10);
		
		JLabel lblTotalSales = new JLabel("Total Sales:");
		lblTotalSales.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblTotalSales.setBounds(25, 100, 130, 30);
		contentPanel.add(lblTotalSales);
		
		txtTotalSales = new JTextField();
		txtTotalSales.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtTotalSales.setEditable(false);
		txtTotalSales.setColumns(10);
		txtTotalSales.setBounds(155, 100, 250, 30);
		contentPanel.add(txtTotalSales);
		
		JLabel lblTotalCommission = new JLabel("Total Commission:");
		lblTotalCommission.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblTotalCommission.setBounds(25, 140, 130, 30);
		contentPanel.add(lblTotalCommission);
		
		txtTotalCommission = new JTextField();
		txtTotalCommission.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtTotalCommission.setEditable(false);
		txtTotalCommission.setColumns(10);
		txtTotalCommission.setBounds(155, 140, 250, 30);
		contentPanel.add(txtTotalCommission);
		
		JLabel lblTotalEarnings = new JLabel("Total Earnings:");
		lblTotalEarnings.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblTotalEarnings.setBounds(25, 180, 130, 30);
		contentPanel.add(lblTotalEarnings);
		
		txtTotalEarnings = new JTextField();
		txtTotalEarnings.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtTotalEarnings.setEditable(false);
		txtTotalEarnings.setColumns(10);
		txtTotalEarnings.setBounds(155, 180, 250, 30);
		contentPanel.add(txtTotalEarnings);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnExport = new JButton("Export");
				btnExport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String print = "--REPORT--" + 
								"\nEmployee number: " + txtEmployeeNum.getText() +
								"\nTotal Sales: " + txtTotalSales.getText() +
								"\nTotal Commission: " + txtTotalCommission.getText() +
								"\nTotal Earnings: " + txtTotalEarnings.getText() + "\n";
						try {
							//Create filechooser, set default directory and only show .txt file
							JFileChooser fileDialog = new JFileChooser();
							fileDialog.setCurrentDirectory(new File("./"));
							fileDialog.addChoosableFileFilter(new TextFilter());
							fileDialog.setAcceptAllFileFilterUsed(false);
							
							//get file
							int returnVal = fileDialog.showSaveDialog(null);
							File file;
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								file = fileDialog.getSelectedFile();
							} else return;
							
							//check regexp
							String fileName = file.getName();
							if (!fileName.matches("[a-zA-Z0-9]+\\.txt")) {
								if (fileName.matches("[a-zA-Z0-9]+")) {
									file = new File(file + ".txt");
								}
								else throw new Exception("The file name is illegal!");
							}
							
							//call helper to write the list to a file
							SaveLoadHelper helper = new SaveLoadHelper(file, SaveLoadHelper.WRITER_MODE);
							helper.writeText(print);
							
							//notify the user
							JOptionPane.showMessageDialog(null, "The report has been saved as \"" +
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
				btnExport.setFont(new Font("Georgia", Font.PLAIN, 16));
				btnExport.setActionCommand("OK");
				buttonPane.add(btnExport);
				getRootPane().setDefaultButton(btnExport);
			}
			{
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnClose.setFont(new Font("Georgia", Font.PLAIN, 16));
				btnClose.setActionCommand("Cancel");
				buttonPane.add(btnClose);
			}
		}
	}
}
