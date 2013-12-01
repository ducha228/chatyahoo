package controlRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import model.ChatHistory;
import model.Message;
import model.SmileIcon;
import model.User;

 
public interface RMILoginInterface extends Remote{
	public String checkLogin(User user) throws RemoteException;
	public User searchUser(String userName) throws RemoteException;
	public Vector<User> listUser() throws RemoteException;
	public boolean addNewUser(User user) throws RemoteException;
	public ChatHistory searchHistory(Message msg) throws RemoteException;
	public void insertHistory(Message msg) throws Exception;
	public String insertFriendList(Message msg) throws Exception;
	public Vector<SmileIcon> listSmileIcon() throws RemoteException;
}
