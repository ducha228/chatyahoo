package controlClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import controlServer.ServerTCPControl;
import model.Message;
import model.Setting;
import model.User;
import view.LoginView;
import view.MainViewYahoo;
import view.RegisterView;

public class LoginControl {

	Connection con;
	LoginView logInView;

	public LoginControl() {
		// TODO Auto-generated constructor stub
	}

	public LoginControl(final LoginView logInView) {
		super();
		this.logInView = logInView;
		logInView.addActionLogin(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				User user = logInView.getUser();
				try {
					Socket mySocket = new Socket(ServerTCPControl.gethost(),
							ServerTCPControl.serverPort);
					ObjectOutputStream oos = new ObjectOutputStream(mySocket
							.getOutputStream());
					Object obj = user;
					Message msg = new Message(Setting.REQUEST_LOGIN, obj, user.getUserName(), null);
					ObjectInputStream ois = new ObjectInputStream(mySocket
							.getInputStream());
					oos.writeObject(msg);
					ClientUser cu = new ClientUser(ois, oos, msg, logInView);
				} catch (Exception e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.logInView.addActionRegister(new RegisterAction());
	} 
	
	class RegisterAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			RegisterView rv = new RegisterView();
			rv.setVisible(true);
			new RegisterController(rv);
		}
		
	}
	public static void main(String[] args) {
		LoginView view = new LoginView();
		new LoginControl(view);
		view.setVisible(true);
	}

}
