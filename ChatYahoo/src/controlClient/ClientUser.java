package controlClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import view.LoginView;
import view.MainChat;
import view.MainViewYahoo;
import model.Message;
import model.Setting;
import model.User;

public class ClientUser extends Thread {

	ObjectInputStream ois;
	ObjectOutputStream oos;
	Message message;
	LoginView view;
	MainViewYahoo mainviewYh;
	MainChat mainChatviewA;
	MainChat mainChatviewB;

	public ClientUser(ObjectInputStream ois, ObjectOutputStream oos,
			Message message, LoginView view) {
		super();
		this.ois = ois;
		this.oos = oos;
		this.message = message;
		this.view = view;
		this.start();
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

	public void run() {
		while (true) {
			Message msg = recieveMsg();
			int flag = msg.getType();
			System.out.println("flag: " + flag);
			switch (flag) {
			case Setting.RESPONSE_LOGIN:
				String result = null;
				try {
					result = (String) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result.equals("YES")) {

					Object obj = msg.getObj();
					User user = (User) obj;
					mainviewYh = new MainViewYahoo(user);
					mainviewYh.setVisible(true);
					MainViewControl mvcontrol = new MainViewControl(mainviewYh,
							ois, oos);
					view.setVisible(false);
				} else {
					view.showMessage("tai khoan khong hop le");
				}
				break;
			case Setting.RESPNONSE_ALL_ONLINE:
				Vector<String> vec = (Vector<String>) msg.getObj();
				mainviewYh.UpdatelistOnline(vec);
				break;
			case Setting.RESPONSE_ACCESS_DATABASE:
				Message msgAResponeAcess = recieveMsg();
				Message msgBResponeAcess = recieveMsg();
				User userAResponeAcess = (User) msgAResponeAcess.getObj();
				User userBResponeAcess = (User) msgBResponeAcess.getObj();
				mainChatviewA = new MainChat(userAResponeAcess, userBResponeAcess);
				mainChatviewA.setVisible(true);
				MainChatControl mainChatcontrolA = new MainChatControl(mainChatviewA, ois, oos);
				break;
			default:
				break;
			}
		}
	}
}
