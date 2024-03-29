package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controlClient.ImageManager;
import model.User;

public class MainViewYahoo extends JFrame {

	private JLabel lblAvatar, lblUserName;
	private PlaceholderTextField txtStatus, txtSearch;
	private JButton btnAddfriend, btnHistory;
	private JList<String> listFriends;
	private JLabel lblBanner;
	private DefaultListModel dlm;
	JComboBox<String> cbbUserName;
	private User user;
	private Image avatar;
	public MainViewYahoo(User user) {
		// TODO Auto-generated constructor stub
		super("ChatOnline");
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
		MyPanel pnlChat = new MyPanel("backgroundview2.jpg");
		pnlChat.setPreferredSize(new Dimension(250, 500));

		// ---------------------------avata+UserName--------------------------

		lblAvatar = new JLabel();
		lblAvatar.setBounds(25, 10, 50, 50);
		lblAvatar.setIcon(new ImageIcon("avatar.jpg"));
		pnlChat.add(lblAvatar);

		lblUserName = new JLabel(user.getUserName());
		lblUserName.setBounds(80, 10, 150, 30);
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setForeground(Color.red);
		//pnlChat.add(lblUserName);
		cbbUserName = new JComboBox<>();
		cbbUserName.addItem(user.getUserName());
		cbbUserName.addItem("Online");
		cbbUserName.addItem("Offline");

		// --------------------------------status---------------------------------

		txtStatus = new PlaceholderTextField("");
		txtStatus.setPlaceholder("What is in your mind");
		txtStatus.setBounds(80, 40, 150, 30);
		pnlChat.add(txtStatus);
		txtStatus.setEditable(true);
		txtStatus.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					System.out.println(txtStatus.getText());
					txtStatus.requestFocus();
					txtStatus.getCaretPosition();
				}
			}
		});

		// ------------------------------------search-------------------------------

		txtSearch = new PlaceholderTextField();
		txtSearch.setPlaceholder("Type your key here");
		txtSearch.setBounds(new Rectangle(0, 100, 200, 30));
		pnlChat.add(txtSearch);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("addfriend.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		image = ImageManager.getScaledImage(image, 30, 30);
		btnAddfriend = new JButton(new ImageIcon(image));
		btnAddfriend.setBounds(new Rectangle(200, 100, 30, 30));
		pnlChat.add(btnAddfriend);

		dlm = new DefaultListModel<>();
		listFriends = new JList(dlm);
		listFriends.setBounds(new Rectangle(10, 135, 230, 300));
		JScrollPane js = new JScrollPane(listFriends);
		js.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//		JPanel pnlChat = new JPanel();
//		pnlChat.add(js);
		js.setBounds(listFriends.getBounds());
		listFriends.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlChat.add(js);
		this.setLocation(600, 200);
		this.setContentPane(pnlChat);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String getInforAcombobox() {
		return (String) cbbUserName.getSelectedItem();
	}
	
	public void ChangeItemStatement(ActionListener act) {
		cbbUserName.addActionListener(act);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addFriendAction(ActionListener act) {
		btnAddfriend.addActionListener(act);
	}
	public void addActionListFriend(MouseAdapter act) {
		listFriends.addMouseListener(act);
	}
	public void addUsertoListFriends(String userName) {
		dlm.addElement(userName);
	}
	
	public String getUserNameA() {
		return lblUserName.getText();
	}
	public void UpdatelistOnline(Vector<String> vec) {
		dlm.removeAllElements();
		for (String string : vec) {
			dlm.addElement(string);
		}
	}
	public void addChooseAvatar(MouseListener ml) {
		lblAvatar.addMouseListener(ml);
	}
	
	public File showAvatarChooser() {
		JFileChooser filechooser = new JFileChooser();
//		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Images",
//				"jpg", "png", "gif", "bmp"));
		int returnVal = filechooser.showOpenDialog(MainViewYahoo.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
//			String mimeType = new MimetypesFileTypeMap().getContentType(file);
//			   // mimeType should now be something like "image/png"
//
//			   if(mimeType.substring(0,5).equalsIgnoreCase("image")){
//			         // its an image
//				   return file;
//			   } else {
//				   showAvatarChooser();
//			   }
			return file;
		}
		return null;
	}
	
	public void setAvatar(BufferedImage avatar) {
		avatar = ImageManager.getScaledImage(avatar, lblAvatar.getWidth(), lblAvatar.getHeight());
		lblAvatar.setIcon(new ImageIcon(avatar));
	}

	public void setDefaultAvatar() {
		lblAvatar.setIcon(new ImageIcon("avatar.jpg"));
	}

}
