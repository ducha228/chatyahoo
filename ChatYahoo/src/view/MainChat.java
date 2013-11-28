package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import model.User;

public class MainChat extends JFrame {

	private JLabel lblavataA, lblavataB;
	private JLabel lblnickNameA, lblBChat;
	private JTextArea txaChat;
	private JTextArea txtInputChat;
	private User userA, userB;
	private JButton btnSend;
	private String message;

	public MainChat(User userA, User userB) {
		// TODO Auto-generated constructor stub
		super(userA.getUserName());
		this.userA = userA;
		this.userB = userB;
		JPanel pnlMain = new JPanel(null);
		pnlMain.setPreferredSize(new Dimension(600, 550));
		// ----------------------set UserNameA----------------------

		lblnickNameA = new JLabel(userB.getUserName());
		lblnickNameA.setBounds(10, 80, 100, 30);
		pnlMain.add(lblnickNameA);

		// ----------------------set txaChat------------------------

		txaChat = new JTextArea();
		txaChat.setBounds(new Rectangle(5, 120, 450, 250));
		txaChat.setEditable(false);
		txaChat.setLineWrap(true);
		txaChat.setWrapStyleWord(true);
		JScrollPane js = new JScrollPane(txaChat);
		js.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//		JPanel pnlChat = new JPanel();
//		pnlChat.add(js);
		js.setBounds(txaChat.getBounds());
		txaChat.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlMain.add(js);

		// ----------------------set txtMainChat--------------------

		txtInputChat = new JTextArea();
		txtInputChat.setBounds(new Rectangle(5, 430, 450, 100));
		txtInputChat.setLineWrap(true);
		txtInputChat.setWrapStyleWord(true);
		txtInputChat.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlMain.add(txtInputChat);

		// ---------------------------setUserAvata------------------------

		lblavataA = new JLabel(new ImageIcon("UserDefault.jpg"));
		lblavataA.setBounds(new Rectangle(470, 120, 115, 115));
		pnlMain.add(lblavataA);

		lblavataB = new JLabel(new ImageIcon("UserDefault.jpg"));
		lblavataB.setBounds(new Rectangle(470, 255, 115, 115));
		pnlMain.add(lblavataB);

		// -------------------------Button Send------------------------

		btnSend = new JButton("Send");
		btnSend.setBounds(new Rectangle(470, 450, 100, 70));
		pnlMain.add(btnSend);

		this.setContentPane(pnlMain);
		this.pack();
	}

	// --------------------------bat su kien ban phim -----------------------
	public void addKeyInputEvent(KeyAdapter act) {
		txtInputChat.addKeyListener(act);
	}

	public void updateEnter() {

		System.out.println(txtInputChat.getText());
		message = txtInputChat.getText();
		txaChat.append(userA.getUserName() + ": " + txtInputChat.getText()+"\n");
		txtInputChat.setText("");
	}
	
	//append vao hop chat
	public void appendChat(User user,String s) {
		txaChat.append(user.getUserName() + ": " + s+"\n");
	}
	
	public String getMsg() {
		return txtInputChat.getText();
	}

	public User getUserA() {
		return userA;
	}

	public void setUserA(User userA) {
		this.userA = userA;
	}

	public User getUserB() {
		return userB;
	}

	public void setUserB(User userB) {
		this.userB = userB;
	}

	public static void main(String[] args) {
		User userA = new User();
		userA.setUserName("tung");
		User userB = new User();
		userB.setUserName("Huong");
		new MainChat(userA, userB).setVisible(true);
	}
}
