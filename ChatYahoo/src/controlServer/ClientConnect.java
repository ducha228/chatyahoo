package controlServer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

import javax.imageio.ImageIO;

import model.ChatHistory;
import model.Message;
import model.Setting;
import model.User;
import controlRMI.RMILoginInterface;

public class ClientConnect extends Thread {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	ServerTCPControl serverTCP;
	private RMILoginInterface rmiServer;
	private Registry registry;
	private String rmiService = "rmitcpLoginServer";
	private int serverRMIPort = 3535;
	private Vector<String> vecChatting;
	private String serverRMIHost = "localhost";

	public ClientConnect(ObjectInputStream ois, ObjectOutputStream oos,
			ServerTCPControl serverTCP) {
		// TODO Auto-generated constructor stub
		this.ois = ois;
		this.oos = oos;
		this.serverTCP = serverTCP;
		vecChatting = new Vector<>();
		bindingRMI();
		this.start();
	}

	private void bindingRMI() {
		try {
			// lay the dang ki
			registry = LocateRegistry.getRegistry(serverRMIHost, serverRMIPort);
			// tim kiem RMI server
			rmiServer = (RMILoginInterface) (registry.lookup(rmiService));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public Message readMsg() {
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

	public void sendMessage(Message msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Image receiveImage() {
		try {
			BufferedImage img = ImageIO.read(ImageIO
					.createImageInputStream(ois));
			Image image = img;
			File file = new File(fileName + "." + ext);
			BufferedImage image = toBufferedImage(file);
			ImageIO.write(image, ext, file);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void sendImage(Image image) {
		try {
			BufferedImage bimg = ImageIO.read(new File(
					"D:\\adi-siddhi\\DSC02503.JPG"));
			ImageIO.write(bimg, "JPG", oos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Message msg = (Message) ois.readObject();
				int flag = msg.getType();
				switch (flag) {
				case Setting.REQUEST_LOGIN:
					User user = (User) msg.getObj();
					try {
						String result = rmiServer.checkLogin(user);
						if (result.equals("YES")) {
							serverTCP.hash.put(user.getUserName(), this);
							System.out.println(serverTCP.getVecOnline().size());
						}
						msg.setType(Setting.RESPONSE_LOGIN);
						sendMessage(msg);
						sendString(result);
						serverTCP.sendAllOnline();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case Setting.REQUSET_ACCESS_DATABASE:
					try {
						User userA = rmiServer.searchUser(String.valueOf(msg
								.getObj()));
						System.out.println(userA.getUserName());
						Message msgFlag = new Message(
								Setting.RESPONSE_ACCESS_DATABASE, null, null,
								null);
						sendMessage(msgFlag);
						Message msgArespone = new Message(
								Setting.RESPONSE_ACCESS_DATABASE, userA, null,
								userA.getUserName());
						sendMessage(msgArespone);
						Message msgB = readMsg();
						User userB = rmiServer.searchUser(String.valueOf(msgB
								.getObj()));
						Message msgBrespone = new Message(
								Setting.RESPONSE_ACCESS_DATABASE, userB, null,
								userA.getUserName());
						sendMessage(msgBrespone);
						System.out.println(userB.getUserName());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Setting.REQUEST_REGISTER:
					User u = (User) msg.getObj();
					boolean b = false;
					try {
						b = rmiServer.addNewUser(u);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message response = new Message();
					response.setType(Setting.RESPONSE_REGISTER);
					response.setSender(null);
					response.setRecipient(null);
					if (b) {
						response.setObj("Ok");
					} else
						response.setObj("Fail");
					sendMessage(response);
					break;
				case Setting.REQUEST_CHAT:
					try {
						rmiServer.insertHistory(msg);
						ChatHistory history = rmiServer.searchHistory(msg);
						User userA = history.getUserA();
						User userB = history.getUserB();
						Message msgSendUserB = new Message(
								Setting.RESPONSE_CHAT, history,
								userA.getUserName(), userB.getUserName());
						serverTCP.sendtoUser(msg.getRecipient(), msgSendUserB);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Setting.REQUEST_SIGNOUT:
					User userout = (User) msg.getObj();
					serverTCP.removeUserOut(userout);
					serverTCP.sendAllOnline();
					serverTCP.sendAllUserOffline(userout);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("out");
				break;
			}
		}
	}

	public void sendString(String s) {
		try {
			oos.writeObject(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
