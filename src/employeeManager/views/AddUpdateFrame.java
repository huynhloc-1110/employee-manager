package employeeManager.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;

import java.text.SimpleDateFormat;

public class AddUpdateFrame extends JDialog {
	
	private static final long serialVersionUID = 2056197338242455814L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JRadioButton rbMale;
	private JRadioButton rbFemale;
	private final ButtonGroup rbgGender = new ButtonGroup();
	private JSpinner spDoB;
	private JLabel lblAddress;
	private JTextArea txtAddress;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JComboBox<String> cbbType;
	private JLabel lblSales;
	private JLabel lblRate;
	private JTextField txtSales;
	private JTextField txtRate;
	private JTextField txtBaseSalary;

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
			AddUpdateFrame dialog = new AddUpdateFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUpdateFrame() {
		initialize();
		createEvents();
	}

	private void initialize() {
		//main panel
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddUpdateFrame.class.getResource("/employeeManager/resources/logo_16.png")));
		setTitle("Employee Manager - Add/Update");
		setBounds(0, 0, 480, 660);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("EMPLOYEE INFORMATION");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTitle.setBounds(115, 20, 240, 30);
		contentPanel.add(lblTitle);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblId.setBounds(25, 60, 80, 30);
		contentPanel.add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtId.setBounds(115, 60, 200, 30);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblFirstName.setBounds(25, 100, 80, 30);
		contentPanel.add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(115, 100, 200, 30);
		contentPanel.add(txtFirstName);
		
		JLabel lblLastname = new JLabel("LastName:");
		lblLastname.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblLastname.setBounds(25, 140, 80, 30);
		contentPanel.add(lblLastname);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtLastName.setColumns(10);
		txtLastName.setBounds(115, 140, 200, 30);
		contentPanel.add(txtLastName);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblGender.setBounds(25, 180, 80, 30);
		contentPanel.add(lblGender);
		
		rbMale = new JRadioButton("Male");
		rbgGender.add(rbMale);
		rbMale.setSelected(true);
		rbMale.setFont(new Font("Georgia", Font.PLAIN, 14));
		rbMale.setBounds(115, 180, 100, 30);
		contentPanel.add(rbMale);
		
		rbFemale = new JRadioButton("Female");
		rbgGender.add(rbFemale);
		rbFemale.setFont(new Font("Georgia", Font.PLAIN, 14));
		rbFemale.setBounds(215, 180, 100, 30);
		contentPanel.add(rbFemale);
		
		JLabel lblDob = new JLabel("DoB:");
		lblDob.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblDob.setBounds(25, 220, 80, 30);
		contentPanel.add(lblDob);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		spDoB = new JSpinner(new SpinnerDateModel());
		spDoB.setEditor(new JSpinner.DateEditor(spDoB, dateFormat.toPattern()));
		spDoB.setFont(new Font("Georgia", Font.PLAIN, 14));
		spDoB.setBounds(115, 220, 200, 30);
		contentPanel.add(spDoB);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblAddress.setBounds(25, 260, 80, 30);
		contentPanel.add(lblAddress);
		
		txtAddress = new JTextArea();
		txtAddress.setLineWrap(true);
		txtAddress.setWrapStyleWord(true);
		txtAddress.setBounds(115, 260, 320, 70);
		txtAddress.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtAddress.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.add(txtAddress);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblPhone.setBounds(25, 340, 80, 30);
		contentPanel.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(115, 340, 200, 30);
		contentPanel.add(txtPhone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblEmail.setBounds(25, 380, 80, 30);
		contentPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(115, 380, 200, 30);
		contentPanel.add(txtEmail);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblType.setBounds(25, 420, 80, 30);
		contentPanel.add(lblType);
		
		cbbType = new JComboBox<String>();
		cbbType.setFont(new Font("Georgia", Font.PLAIN, 14));
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Commission Employee", "Base Plus Commission Employee"}));
		cbbType.setBounds(115, 420, 240, 30);
		contentPanel.add(cbbType);
		
		lblSales = new JLabel("Sales:");
		lblSales.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblSales.setBounds(25, 460, 80, 30);
		contentPanel.add(lblSales);
		
		lblRate = new JLabel("Rate:");
		lblRate.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblRate.setBounds(25, 500, 80, 30);
		contentPanel.add(lblRate);
		
		txtSales = new JTextField();
		txtSales.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtSales.setColumns(10);
		txtSales.setBounds(115, 460, 200, 30);
		contentPanel.add(txtSales);
		
		txtRate = new JTextField();
		txtRate.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtRate.setColumns(10);
		txtRate.setBounds(115, 500, 200, 30);
		contentPanel.add(txtRate);
		
		JLabel lblBaseSalary = new JLabel("Base salary:");
		lblBaseSalary.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblBaseSalary.setBounds(25, 540, 80, 30);
		contentPanel.add(lblBaseSalary);
		
		txtBaseSalary = new JTextField();
		txtBaseSalary.setEnabled(false);
		txtBaseSalary.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtBaseSalary.setColumns(10);
		txtBaseSalary.setBounds(115, 540, 200, 30);
		contentPanel.add(txtBaseSalary);
		
		//button panel
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnSave.setActionCommand("OK");
		buttonPane.add(btnSave);
		getRootPane().setDefaultButton(btnSave);
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);
	}
	
	private void createEvents() {
		cbbType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (((String)cbbType.getSelectedItem()).equals("Commission Employee")) {
					txtBaseSalary.setText("");
					txtBaseSalary.setEnabled(false);
				} else
					txtBaseSalary.setEnabled(true);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCanceled = true;
				setVisible(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check for blank input
				JTextComponent[] txtArray = new JTextComponent[8];
				txtArray[0] = txtId;
				txtArray[1] = txtFirstName;
				txtArray[2] = txtLastName;
				txtArray[3] = txtAddress;
				txtArray[4] = txtPhone;
				txtArray[5] = txtEmail;
				txtArray[6] = txtSales;
				txtArray[7] = txtRate;
				for (JTextComponent txt : txtArray) {
					if (txt.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Missing input!", "Error",
								JOptionPane.ERROR_MESSAGE);
						txt.requestFocusInWindow();
						return;
					}
				}
				
				//simple phone reg validation
				if (!txtPhone.getText().matches("0[0-9]{9}")) {
					JOptionPane.showMessageDialog(null, "Invalid phone number!", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtPhone.requestFocusInWindow();
					txtPhone.selectAll();
					return;
				}
				
				//Email regex validation by RFC5322
				if (!txtEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
					JOptionPane.showMessageDialog(null, "Invalid email address!", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtEmail.requestFocusInWindow();
					txtEmail.selectAll();
					return;
				}
				
				//Sales number checking
				try {
					double sales = Double.parseDouble(txtSales.getText());
					if (sales < 0) {
						throw new Exception();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid sales!", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtSales.requestFocusInWindow();
					txtSales.selectAll();
					return;
				}
				
				//Rate number checking
				try {
					double rate = Double.parseDouble(txtRate.getText());
					if (rate < 0 || rate > 1.0) {
						throw new Exception();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid commission rate!", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtRate.requestFocusInWindow();
					txtRate.selectAll();
					return;
				}
				
				//check for base salary if combobox == "Base Plus Commission"
				String temp = (String) cbbType.getSelectedItem();
				if (temp.equals("Base Plus Commission Employee")) {
					try {
						if (txtBaseSalary.getText().isEmpty()) throw new Exception();
						double baseSalary = Double.parseDouble(txtBaseSalary.getText());
						if (baseSalary < 0) throw new Exception();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Missing or invalid base salary!", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtBaseSalary.requestFocusInWindow();
						txtBaseSalary.selectAll();
						return;
					}
				}
				
				//close this frame
				isCanceled = false;
				setVisible(false);
			}
		});
	}
	
	//getters for fields
	public JTextField getTxtId() {
		return txtId;
	}
	public JTextField getTxtFirstName() {
		return txtFirstName;
	}
	public JTextField getTxtLastName() {
		return txtLastName;
	}
	public JRadioButton getRbMale() {
		return rbMale;
	}
	public JRadioButton getRbFemale() {
		return rbFemale;
	}
	public JSpinner getSpDoB() {
		return spDoB;
	}
	public JTextArea getTxtAddress() {
		return txtAddress;
	}
	public JTextField getTxtPhone() {
		return txtPhone;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	public JComboBox<String> getCbbType() {
		return cbbType;
	}
	public JTextField getTxtSales() {
		return txtSales;
	}
	public JTextField getTxtRate() {
		return txtRate;
	}
	public JTextField getTxtBaseSalary() {
		return txtBaseSalary;
	}
	
	//to indicate if the user click "x" or "cancel"
	public boolean isCanceled = true;
}
