package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import controlClient.ImageManager;
import controlClient.SplitString;
import model.SmileIcon;
import model.User;

public class MainChat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8342601278777698328L;
	private JLabel lblavataA, lblavataB;
	private JLabel lblnickNameA, lblBChat;
	private JTextPane txpChat;
	private JTextArea txtInputChat;
	private User userA, userB;
	private JButton btnSend;
	private String message;
	private Vector<SmileIcon> smileicon;

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

		txpChat = new JTextPane();
		txpChat.setBounds(new Rectangle(5, 120, 450, 250));
		txpChat.setEditable(false);
		// txpChat.setLineWrap(true);
		// txpChat.setWrapStyleWord(true);
		JScrollPane js = new JScrollPane(txpChat);
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// JPanel pnlChat = new JPanel();
		// pnlChat.add(js);
		js.setBounds(txpChat.getBounds());
		txpChat.setBorder(BorderFactory.createLineBorder(Color.black));
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
		String message = txtInputChat.getText();
		StyledDocument doc = processString(userA, message);
		txpChat.setStyledDocument(doc);
		txtInputChat.setText("");
	}

	// append vao hop chat
	public void appendChat(User user, String s) {
		StyledDocument doc = processString(user, s);
		txpChat.setStyledDocument(doc);
		// txaChat.append(user.getUserName() + ": " + s+"\n");
	}

	public StyledDocument processString(User user, String message) {
		StyleContext context = new StyleContext();
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontSize(style, 14);
		StyleConstants.setSpaceAbove(style, 4);
		StyleConstants.setSpaceBelow(style, 4);
		
		SimpleAttributeSet normalAttributes = new SimpleAttributeSet();
		SimpleAttributeSet headerAttributes = new SimpleAttributeSet();
		StyleConstants.setBold(headerAttributes, true);
		StyleConstants.setFontSize(headerAttributes, 16);
		
		StyledDocument doc = txpChat.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), user.getUserName() + ": ", headerAttributes);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		// System.out.println(smileicon.size());
		Vector<String> splitStrs = SplitString.splitString(smileicon, message);

		for (String str : splitStrs) {

			boolean isicon = false;
			for (SmileIcon si : smileicon) {
				if (si.getShortKey().equals(str)) {
					Style labelStyle = context
							.getStyle(StyleContext.DEFAULT_STYLE);
					Icon icon = new ImageIcon("smile/" + si.getIconImage()
							+ ".png");
					JLabel label = new JLabel(icon);
					StyleConstants.setComponent(labelStyle, label);
					try {
						doc.insertString(doc.getLength(), "Ignored", labelStyle);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					isicon = true;
					break;
				}
			}
			if (!isicon) {
				System.out.println(str + doc.getLength());
				try {
					doc.insertString(doc.getLength(), str, normalAttributes);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			isicon = false;
		}
		try {
			doc.insertString(doc.getLength(), "\n", style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
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

	public void setAvatarA(BufferedImage bimg) {
		bimg = ImageManager.getScaledImage(bimg, lblavataA.getWidth(),
				lblavataA.getHeight());
		lblavataA.setIcon(new ImageIcon(bimg));
	}

	public void setAvatarB(BufferedImage bimg) {
		bimg = ImageManager.getScaledImage(bimg, lblavataB.getWidth(),
				lblavataB.getHeight());
		lblavataB.setIcon(new ImageIcon(bimg));
	}

	public void setSmileIcon(Vector<SmileIcon> smileIcon) {
		smileicon = smileIcon;
	}
}
