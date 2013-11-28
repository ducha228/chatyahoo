package controlClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import sun.nio.cs.HistoricallyNamedCharset;
import view.LoginView;
import view.MainChat;
import view.MainViewYahoo;
import model.ChatHistory;
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
	public static Vector<ChatHistory> vecChat;

	public ClientUser(ObjectInputStream ois, ObjectOutputStream oos,
			Message message, LoginView view) {
		super();
		this.ois = ois;
		this.oos = oos;
		this.message = message;
		this.view = view;
		vecChat = new Vector<>();
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

//	------------------------kiem tra xem da hien thi bang chat chua--------------------s--
	public boolean testChatting(ChatHistory chatHistory) {
		boolean result = false;
		for (ChatHistory item : vecChat) {
			System.out.println(chatHistory.getUserA().getUserName() + "-"
					+ item.getUserA().getUserName());
			System.out.println(chatHistory.getUserB().getUserName() + "-"
					+ item.getUserB().getUserName());
			if (chatHistory.getUserA().getUserName()
					.equals(item.getUserA().getUserName())
					&& chatHistory.getUserB().getUserName()
							.equals(item.getUserB().getUserName())) {
				result = true;
				return result;
			}
		}
		return result;
	}

//	--------------------------------so sanh viewChat---------------------
	public boolean testChatView(MainChat view, ChatHistory history) {
		User userAchat = view.getUserB();
		System.out.println("userAChat : " + userAchat.getUserName());
		User userBchat = view.getUserA();
		User userAhistory = history.getUserA();
		System.out.println("userAhistory : " + userAhistory.getUserName());
		User userBhistory = history.getUserB();
		if(userAchat.getUserName().equals(userAhistory.getUserName()) && userBchat.getUserName().equals(userBhistory.getUserName()))
			return true;
		return false;
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
				mainChatviewA = new MainChat(userAResponeAcess,
						userBResponeAcess);
				mainChatviewA.setVisible(true);
				MainChatControl mainChatcontrolA = new MainChatControl(
						mainChatviewA, ois, oos);
				ChatHistory chathistory = new ChatHistory();
				chathistory.setUserA(userBResponeAcess);
				chathistory.setUserB(userAResponeAcess);
				if(testChatting(chathistory) == false){
					vecChat.add(chathistory);
				}
				System.out.println("size vecchat = " + vecChat.size());
				break;
			case Setting.RESPONSE_CHAT:
				ChatHistory history = (ChatHistory) msg.getObj();
				System.out.println(vecChat.size());
				if (testChatting(history)) {
					if (testChatView(mainChatviewA, history)) {
						mainChatviewA.appendChat(history.getUserA(),
								history.getMessage());
					} else {
						mainChatviewB.appendChat(history.getUserA(),
								history.getMessage());
					}
					System.out.println("tung1");
				} else {
					vecChat.add(history);
					mainChatviewB = new MainChat(history.getUserB(),
							history.getUserA());
					mainChatviewB.setVisible(true);
					mainChatviewB.appendChat(history.getUserA(),
							history.getMessage());
					MainChatControl mainChatcontrolB = new MainChatControl(
							mainChatviewB, ois, oos);
					System.out.println("tung2");
				}
			default:
				break;
			}
		}
	}
}
