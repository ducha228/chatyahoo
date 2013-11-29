package controlServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import controlRMI.RMILoginInterface;
import model.Message;
import model.Setting;
import model.User;

public class ServerTCPControl {

	public static int serverPort = 2345;
	private Socket clientSocket;
	ServerSocket myServer;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public static Hashtable<String, ClientConnect> hash;

	public Hashtable<String, ClientConnect> getHash() {
		return hash;
	}

	public void setHash(Hashtable<String, ClientConnect> hash) {
		this.hash = hash;
	}

	public static String gethost() {
		String hostName = "unknown";
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostName = addr.getHostName();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hostName;
	}

	public ServerTCPControl() {
		// TODO Auto-generated constructor stub
		hash = new Hashtable<>();
		openServer(serverPort);
		// bindingRMI();
		while (true) {
			listenning();
		}
	}
	
	public Vector<String> getVecOnline() {
		Vector<String> vec = new Vector<>();
		Enumeration en = hash.keys();
		while (en.hasMoreElements()) {
			String s = (String) en.nextElement();
			vec.add(s);
		}
		return vec;
	}
	
	public void sendMessage(Message msg) {
		 try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendAllOnline() {
		Enumeration en = hash.keys();
		while (en.hasMoreElements()) {
			String s = (String) en.nextElement();
			Message msg = new Message(Setting.RESPNONSE_ALL_ONLINE, getVecOnline(), "", s);
			hash.get(s).sendMessage(msg);
		}
	}
	
	public void sendAllUserOffline(User useroff) {
		Enumeration en = hash.keys();
		while (en.hasMoreElements()) {
			String s = (String) en.nextElement();
			Message msg = new Message(Setting.RESPONSE_USER_OFFLINE, useroff, "", s);
			hash.get(s).sendMessage(msg);
		}
	}
	
	public void sendtoUser(String userName, Message msg) {
		hash.get(userName).sendMessage(msg);
	}

	private void openServer(int portNumber) {
		try {
			myServer = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeUserOut(User out) {
		hash.remove(out.getUserName());
	}

	private void listenning() {
		try {
			clientSocket = myServer.accept();
			ois = new ObjectInputStream(
					clientSocket.getInputStream());
			// User user = (User) ois.readObject();
			oos = new ObjectOutputStream(
					clientSocket.getOutputStream());

			ClientConnect clc = new ClientConnect(ois, oos, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void putUser(User user, ClientConnect clnc) {
		hash.put(user.getUserName(), clnc);
	}

	public static void main(String[] args) {
		new ServerTCPControl();
	}
}
