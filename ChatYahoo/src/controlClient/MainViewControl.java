package controlClient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JList;

import model.Message;
import model.Setting;
import model.User;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import view.MainViewYahoo;

public class MainViewControl {

	private MainViewYahoo mainviewyh;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public MainViewControl(final MainViewYahoo mainviewyh,
			ObjectInputStream ois, ObjectOutputStream oos) {
		super();
		this.mainviewyh = mainviewyh;
		this.ois = ois;
		this.oos = oos;
		mainviewyh.addActionListFriend(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						System.out.println("Double-clicked on: " + o.toString());
						Message msgA = new Message(
								Setting.REQUEST_ACCESS_DATABASE, mainviewyh
										.getUserNameA(), mainviewyh
										.getUserNameA(), mainviewyh
										.getUserNameA());
						Message msgB = new Message(
								Setting.REQUEST_ACCESS_DATABASE, o.toString(),
								mainviewyh.getUserNameA(), mainviewyh
										.getUserNameA());
						sendMessage(msgA);
						sendMessage(msgB);
					}
				}
			}
		});
		mainviewyh.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				User userOut = mainviewyh.getUser();
				Message msgout = new Message(Setting.REQUEST_SIGNOUT, userOut,
						userOut.getUserName(), null);
				sendMessage(msgout);
			}
		});
		mainviewyh.addChooseAvatar(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					File file = mainviewyh.showAvatarChooser();
					BufferedImage bimg;
					try {
						bimg = ImageIO.read(file);
						mainviewyh.setAvatar(bimg);

						Message message = new Message(Setting.REQUEST_UPLOAD_IMAGE,
								ImageManager.encodeToString(bimg, "jpg"),
								mainviewyh.getUserNameA(), 
								mainviewyh.getUserNameA());
						sendMessage(message);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void sendMessage(Message msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Message receive() {
		Message msg = null;
		try {
			msg = (Message) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
}
