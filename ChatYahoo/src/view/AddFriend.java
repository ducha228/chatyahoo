package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controlClient.ImageManager;
import model.User;

public class AddFriend extends JFrame{

	private JLabel lblAddFriend;
	private JButton btnAddFriend;
	private JTextField txtUserFriend;
	private User user;
	public AddFriend(User user) {
		// TODO Auto-generated constructor stub
		super("Add Friend");
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
		this.user = user;
		MyPanel pnlAdd = new MyPanel("backgroundaddfriend.jpg");
		pnlAdd.setLayout(null);
		pnlAdd.setPreferredSize(new Dimension(200, 120));
		lblAddFriend = new JLabel("Type Your Friend");
		lblAddFriend.setBounds(25, 10, 150, 30);
		lblAddFriend.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlAdd.add(lblAddFriend);
		
		txtUserFriend = new JTextField();
		txtUserFriend.setBounds(10, 70, 150, 30);
		pnlAdd.add(txtUserFriend);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("addfriend.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		image = ImageManager.getScaledImage(image, 30, 30);
		btnAddFriend = new JButton(new ImageIcon(image));
		btnAddFriend.setBounds(170, 70, 30, 30);
		pnlAdd.add(btnAddFriend);
		this.setContentPane(pnlAdd);
		this.setBounds(200, 100, 200, 120);
		this.pack();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addFriendAction(ActionListener act) {
		btnAddFriend.addActionListener(act);
	}
	
	public String getUserNameFriend() {
		return txtUserFriend.getText();
	}
}
