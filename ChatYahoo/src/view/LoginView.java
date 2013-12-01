package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.User;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454502557425549466L;
	private PlaceholderTextField txtUserName;
	private HintedPasswordField txtpassword;
	private JLabel lblUserName, lblPassword, lblIcon;
	private JButton btnLogin, btnRegister;
	private JCheckBox chbRememberpass, chbHidden;

	public LoginView() {
		// TODO Auto-generated constructor stub
		super("Chat server");
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
		MyPanel pnlLogin = new MyPanel("backgroundlogin.jpg");
		pnlLogin.setPreferredSize(new Dimension(250, 450));

		// -----------------Icon--------------------------

		lblIcon = new JLabel(new ImageIcon("chatlogin.png"));
		lblIcon.setBounds(new Rectangle(90, 15, 70, 70));
		pnlLogin.add(lblIcon);

		// -----------------UserName--------------------------

		lblUserName = new JLabel("UserName: ");
		lblUserName.setBounds(0, 150, 70, 30);
		//pnlLogin.add(lblUserName);

		txtUserName = new PlaceholderTextField();
		txtUserName.setPlaceholder("User Name");
		txtUserName.setBounds(75, 150, 100, 30);
		pnlLogin.add(txtUserName);

		// -----------------Password--------------------------

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(new Rectangle(0, 200, 70, 30));
		//pnlLogin.add(lblPassword);

		txtpassword = new HintedPasswordField(20, "Password");
		txtpassword.setEchoChar('*');
		txtpassword.setBounds(new Rectangle(75, 200, 100, 30));
		pnlLogin.add(txtpassword);

		// -----------------CheckBox--------------------------

		chbRememberpass = new JCheckBox("Lưu tên đăng nhập");
		chbRememberpass.setBounds(new Rectangle(60, 250, 230, 30));
		pnlLogin.add(chbRememberpass);
		chbRememberpass.setOpaque(false);

		chbHidden = new JCheckBox("Đăng nhập ẩn");
		chbHidden.setBounds(new Rectangle(60, 280, 230, 30));
		pnlLogin.add(chbHidden);
		chbHidden.setOpaque(false);

		// -----------------Button--------------------------
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(new Rectangle(20, 330, 90, 30));
		pnlLogin.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(new Rectangle(140, 330, 90, 30));
		pnlLogin.add(btnRegister);
		
		this.setLocation(600, 200);
		this.setContentPane(pnlLogin);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addActionLogin(ActionListener act) {
		btnLogin.addActionListener(act);
	}
	
	public void addActionRegister(ActionListener act) {
		btnRegister.addActionListener(act);
	}
	
	public User getUser() {
		User user = new User();
		user.setUserName(txtUserName.getText());
		user.setUserpassword(txtpassword.getText());
		return user;
	}
	
	public void showMessage(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	public static void main(String[] args) {
		new LoginView().setVisible(true);
	}
}
