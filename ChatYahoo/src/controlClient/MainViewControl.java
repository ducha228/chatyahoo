package controlClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JList;

import model.Message;
import model.Setting;
import model.User;
import view.AddFriend;
import view.MainViewYahoo;

public class MainViewControl {

	private MainViewYahoo mainviewyh;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public MainViewControl(final MainViewYahoo mainviewyh,
			final ObjectInputStream ois, final ObjectOutputStream oos) {
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
								Setting.REQUSET_ACCESS_DATABASE, mainviewyh
										.getUserNameA(), mainviewyh
										.getUserNameA(), mainviewyh
										.getUserNameA());
						Message msgB = new Message(
								Setting.REQUSET_ACCESS_DATABASE, o.toString(),
								mainviewyh.getUserNameA(), mainviewyh
										.getUserNameA());
						sendMessage(msgA);
						sendMessage(msgB);
					}
				}
			}
		});
		mainviewyh.addFriendAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddFriend viewaddfriend = new AddFriend(mainviewyh.getUser());
				AddFriendColtrol controlAddfriend = new AddFriendColtrol(viewaddfriend, ois, oos);
				viewaddfriend.setVisible(true);
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
	}

	public void sendMessage(Message msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Message recieveMsg() {
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
