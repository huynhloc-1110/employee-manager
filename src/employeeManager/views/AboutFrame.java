package employeeManager.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;


public class AboutFrame extends JDialog {

	private static final long serialVersionUID = 1291399530437195897L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			AboutFrame dialog = new AboutFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutFrame.class.getResource("/employeeManager/resources/logo_16.png")));
		setTitle("Employee Manager - About");
		setBounds(0, 0, 440, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAbout = new JLabel("ABOUT");
			lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
			lblAbout.setFont(new Font("Georgia", Font.BOLD, 16));
			lblAbout.setBounds(165, 20, 100, 30);
			contentPanel.add(lblAbout);
			
			JTextArea txtrProgramNameEmployee = new JTextArea();
			txtrProgramNameEmployee.setBackground(SystemColor.menu);
			txtrProgramNameEmployee.setMargin(new Insets(20, 20, 20, 20));
			txtrProgramNameEmployee.setEditable(false);
			txtrProgramNameEmployee.setWrapStyleWord(true);
			txtrProgramNameEmployee.setLineWrap(true);
			txtrProgramNameEmployee.setFont(new Font("Georgia", Font.PLAIN, 14));
			txtrProgramNameEmployee.setText("Program Name: Employee Manager\r\nAuthor:  Huynh Loc Le - Student  at FPT Greenwich University at HCMC\r\nRelease Date: March 11, 2022\r\nVersion: 1.0");
			txtrProgramNameEmployee.setBounds(25, 50, 380, 130);
			contentPanel.add(txtrProgramNameEmployee);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(AboutFrame.class.getResource("/employeeManager/resources/employees_160x100.png")));
			lblNewLabel.setBounds(25, 180, 380, 120);
			contentPanel.add(lblNewLabel);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setFont(new Font("Georgia", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
