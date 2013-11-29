package controlRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ChatHistory;
import model.Message;
import model.User;
import controlClient.DBConnection;

public class RMILoginServerControl extends UnicastRemoteObject implements
		RMILoginInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7783505611830213L;
	private int serverPort = 3535;
	private Registry registry;
	private String rmiService = "rmitcpLoginServer";

	protected RMILoginServerControl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		registry = LocateRegistry.createRegistry(serverPort);
		registry.rebind(rmiService, this);
	}

	@Override
	public String checkLogin(User user) throws RemoteException {
		// TODO Auto-generated method stub
		if (checkUser(user))
			return "YES";
		return "NO";
	}

	public boolean checkUser(User user) throws RemoteException {
		Connection conn = DBConnection.getConn();
		String sql = "select * from tbluser where username = ? and userpassword = ?";
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, user.getUserName());
			pre.setString(2, user.getUserpassword());
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exceptionh
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User searchUser(String userName) throws RemoteException {
		// TODO Auto-generated method stub
		User user = new User();
		String  sql = "select * from tbluser where username = ?";
		Connection conn = DBConnection.getConn();
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, userName);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserpassword(rs.getString("userpassword"));
				user.setUserFirstName(rs.getString("userFirstName"));
				user.setUserLastName(rs.getString("userLastName"));
				user.setUserNickName(rs.getString("userNickname"));
				user.setUserEmail(rs.getString("useremail"));
				user.setUserPhoneNumber(rs.getString("userphone"));
				user.setUserDateofBirth(rs.getString("dateOfBirth"));
				user.setGender(rs.getInt("gender"));
				user.setIsOnline(rs.getInt("isOnline"));
				user.setUserStatusUpdate(rs.getString("statusUpdate"));
				user.setUserHost(rs.getString("userHost"));
				user.setUserPort(rs.getInt("userPort"));
				user.setUserAvartarURL(rs.getString("avatarUrl"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static void main(String[] args) {
		try {
			new RMILoginServerControl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean addNewUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConn();
		String sql = "select * from tbluser order by userid";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			String userid = "X0001";
			while(rs.next()) {
				userid = rs.getString("userid");
			}
			System.out.println(userid);
			userid = userid.substring(1, userid.length());
			System.out.println(userid);
			int id = Integer.parseInt(userid);
			id++;
			System.out.println(id);
			if (id <10) 
				userid = "X000"+String.valueOf(id);
			else if (id < 100)
				userid = "X00"+String.valueOf(id);
			else if (id < 1000)
				userid = "X0" +String.valueOf(id);
			else
				userid = "X"+String.valueOf(id);
			
			sql = "insert into tbluser (userid,username,userpassword,userFirstName,userLastName,userphone,dateOfBirth,gender)"
					+ " values(?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setString(2, user.getUserName());
			pstm.setString(3, user.getUserpassword());
			pstm.setString(4, user.getUserFirstName());
			pstm.setString(5, user.getUserLastName());
			pstm.setString(6, user.getUserPhoneNumber());
			pstm.setString(7, user.getUserDateofBirth());
			pstm.setInt(8, user.getGender());
			
			return !pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ChatHistory searchHistory(Message msg) throws RemoteException {
		// TODO Auto-generated method stub
		ChatHistory chatHistory = (ChatHistory) msg.getObj();
		String sql = "select *from tblchathistory where useridA = ? and useridB = ?";
		try {
			Connection conn = DBConnection.getConn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, chatHistory.getUserA().getUserId());
			pre.setString(2, chatHistory.getUserB().getUserId());
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				chatHistory.setId(rs.getString("id"));
				return chatHistory;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ChatHistory();
	}

	public String sizeDBChatHistory() {
		String sql = "select * from tblchathistory";
		int dem = 0;
		try {
			Connection conn = DBConnection.getConn();
			PreparedStatement pre = conn.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				dem++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return String.valueOf(dem);
	}

	@Override
	public void insertHistory(Message msg) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into tblchathistory(id,useridA,useridB,message,time,sender) values (?,?,?,?,?,?)";
		ChatHistory chat = (ChatHistory) msg.getObj();
		try {
			Connection conn = DBConnection.getConn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, sizeDBChatHistory());
			pre.setString(2, chat.getUserA().getUserId());
			pre.setString(3, chat.getUserB().getUserId());
			pre.setString(4, chat.getMessage());
			pre.setString(5, chat.getTime());
			pre.setString(6, chat.getUserSender().getUserId());
			pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
