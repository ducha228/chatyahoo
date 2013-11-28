package controlClient;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import controlServer.ServerTCPControl;
import model.ChatHistory;
import model.Message;
import model.Setting;
import model.User;
import view.MainChat;

public class MainChatControl {

	private MainChat view;
	private int serverPort = 2345;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public MainChatControl(final MainChat view,final ObjectInputStream ois,final
			ObjectOutputStream oos) {
		super();
		this.view = view;
		this.ois = ois;
		this.oos = oos;
		view.addKeyInputEvent(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					User userA = view.getUserA();
					User userB = view.getUserB();
					String textsend = view.getMsg();
					view.updateEnter();
					Calendar cal = Calendar.getInstance();
			    	cal.getTime();
			    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			    	System.out.println(sdf.format(cal.getTime()));
			    	String time = String.valueOf(sdf.format(cal.getTime()));
					ChatHistory history = new ChatHistory(textsend, time, userA, userB, userA);
					Message msg = new Message(Setting.REQUEST_CHAT, history, userA.getUserName(), userB.getUserName());
					sendMessage(msg);
					System.out.println("chat");
				}
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
	
	public Message msgRecieve() {
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

//	public static void main(String[] args) {
//		User userA = new User();
//		userA.setUserName("tung");
//		User userB = new User();
//		MainChat view = new MainChat(userA, userB);
//		new MainChatControl(view);
//		view.setVisible(true);
//		User userA1 = new User();
//		userA1.setUserName("khoa");
//		User userB1 = new User();
//		MainChat view1 = new MainChat(userA1, userB1);
//		new MainChatControl(view1);
//		view1.setVisible(true);
//	}

}
