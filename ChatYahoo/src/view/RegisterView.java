package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.User;

public class RegisterView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 30;
	private JLabel lblIcon, lblRegister,lblDateOfBirth;
	private PlaceholderTextField txtfirstName, txtLastName, txtUserName, txtPhone;
	private HintedPasswordField txtpassword;
	private JComboBox<String> jcbdate, jcbmonth, jcbyear;
	private JRadioButton maleRadio,femaleRadio;
	private JButton btnCreate;

	public RegisterView() {
		// TODO Auto-generated constructor stub
		super("Register User");
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MyPanel pnlRegister = new MyPanel("backgroundRegister.jpg");
		pnlRegister.setPreferredSize(new Dimension(400, 500));

		// -----------------Icon--------------------------
		lblIcon = new JLabel(new ImageIcon("iconChat1.jpg"));
		lblIcon.setBounds(new Rectangle(0, 0, 50, 50));
		pnlRegister.add(lblIcon);
		
		// -----------------Label Register---------------
		lblRegister = new JLabel();
		lblRegister.setText("Register");
		lblRegister.setBounds(100, 50, WIDTH, HEIGHT);
		
		pnlRegister.add(lblRegister);
		
		// -----------------First Name---------------
		txtfirstName = new PlaceholderTextField();
		txtfirstName.setPlaceholder("First Name");
		txtfirstName.setBounds(100,100,WIDTH,HEIGHT);
		
		pnlRegister.add(txtfirstName);
		
		// -----------------Last Name-----------------
		txtLastName = new PlaceholderTextField();
		txtLastName.setPlaceholder("Last Name");
		txtLastName.setBounds(100, 140, WIDTH, HEIGHT);
		pnlRegister.add(txtLastName);
		
		// -----------------User Name----------------
		txtUserName = new PlaceholderTextField();
		txtUserName.setPlaceholder("User Name");
		txtUserName.setBounds(100,180,WIDTH,HEIGHT);
		pnlRegister.add(txtUserName);
		
		// -----------------Password-----------------
		txtpassword = new HintedPasswordField(20, "Password");
		txtpassword.setBounds(100, 220, WIDTH, HEIGHT);
		pnlRegister.add(txtpassword);
		
		// ----------------Phone Number-----------------
		txtPhone = new PlaceholderTextField();
		txtPhone.setPlaceholder("Phone Number");
		txtPhone.setBounds(100,260,WIDTH,HEIGHT);
		pnlRegister.add(txtPhone);
		
		// --------------Date Of Birth----------------
		lblDateOfBirth = new JLabel("Birth Day");
		lblDateOfBirth.setBounds(100,300,WIDTH,HEIGHT);
		pnlRegister.add(lblDateOfBirth);
		
		// --------------Date---------------
		jcbdate = new JComboBox<>();
		Vector<String> dateVec = new Vector<>();
		for (int i = 1; i < 31; i++)
			dateVec.add(String.valueOf(i));
		jcbdate.setModel(new DefaultComboBoxModel<>(dateVec));
		jcbdate.setBounds(170,300,40,30);
		pnlRegister.add(jcbdate);
		
		// --------------Month--------------
		jcbmonth = new JComboBox<>();
		jcbmonth.setModel(new DefaultComboBoxModel<>(new String[] {
				"January","February","March","April","May","June","July","August","September",
				"October","November","December"
		}));
		jcbmonth.setBounds(215,300,90,30);
		pnlRegister.add(jcbmonth);
		
		// -------------Year--------------
		jcbyear = new JComboBox<>();
		Vector<String> yearVec = new Vector<>();
		for (int i = 1900; i < 2014; i++)
			yearVec.add(String.valueOf(i));
		jcbyear.setModel(new DefaultComboBoxModel<>(yearVec));
		jcbyear.setBounds(310,300,55,30);
		pnlRegister.add(jcbyear);
		
		// -------------Gender---------------
		maleRadio = new JRadioButton();
		maleRadio.setSelected(true);
		maleRadio.setActionCommand("male");
		maleRadio.setText("Male");
		maleRadio.setBounds(100,340,70,HEIGHT);
		pnlRegister.add(maleRadio);
		
		femaleRadio = new JRadioButton();
		//femaleRadio.setSelected(false);
		femaleRadio.setActionCommand("female");
		femaleRadio.setText("Female");
		femaleRadio.setBounds(230,340,70,HEIGHT);
		pnlRegister.add(femaleRadio);
		
		ButtonGroup group = new ButtonGroup();
		group.add(maleRadio);
		group.add(femaleRadio);
		
		// -------------Button Register------------
		btnCreate = new JButton("Register");
		btnCreate.setBounds(150,380,WIDTH/2,HEIGHT);
		pnlRegister.add(btnCreate);
		
		// -----------------
		this.setContentPane(pnlRegister);
		this.setLocation(350,150);
		this.pack();
		//System.out.println(this.size().width);
	}
	
	public User getUser() {
		User user = new User();
		user.setUserFirstName(txtfirstName.getText());
		user.setUserLastName(txtLastName.getText());
		user.setUserName(txtUserName.getText());
		user.setUserpassword(txtpassword.getText());
		int gender = 1;
		if (maleRadio.isSelected())
			gender = 1;
		else 
			gender = 0;
		user.setGender(gender);
		user.setUserPhoneNumber(txtPhone.getText());
		String date = (String) jcbdate.getSelectedItem();
		String month = (String) jcbmonth.getSelectedItem();
		String year = (String) jcbyear.getSelectedItem();
		String dateofbirth = date + "/" + month + "/" + year;
		user.setUserDateofBirth(dateofbirth);
		
		return user;
	}
	
	public void addRegisterAction (ActionListener al) {
		btnCreate.addActionListener(al);
	}
	
	public int showMessage(String message) {
		return JOptionPane.showConfirmDialog(null, message, "Register Success", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void main(String[] args) {
		new RegisterView().setVisible(true);
		
	}
}
