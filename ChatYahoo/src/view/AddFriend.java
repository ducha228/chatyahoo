package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;

public class AddFriend extends JFrame{

	private JLabel lblAddFriend;
	private JButton btnAddFriend;
	private JTextField txtUserFriend;
	private User user;
	public AddFriend(User user) {
		// TODO Auto-generated constructor stub
		super("Add Friend");
		this.user = user;
		JPanel pnlAdd = new JPanel(null);
		pnlAdd.setPreferredSize(new Dimension(300, 300));
		lblAddFriend = new JLabel("Nhập UserName cua bạn muốn kết bạn");
		lblAddFriend.setBounds(50, 50, 250, 30);
		pnlAdd.add(lblAddFriend);
		
		txtUserFriend = new JTextField();
		txtUserFriend.setBounds(50, 100, 150, 30);
		pnlAdd.add(txtUserFriend);
		
		btnAddFriend = new JButton("Add");
		btnAddFriend.setBounds(210, 100, 100, 30);
		pnlAdd.add(btnAddFriend);
		this.setContentPane(pnlAdd);
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
