package controlClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import javax.swing.JOptionPane;

import controlServer.ServerTCPControl;
import model.Message;
import model.Setting;
import model.User;
import view.RegisterView;

public class RegisterController {
	public RegisterView rv;
	public RegisterController(RegisterView rv) {
		// TODO Auto-generated constructor stub
		this.rv = rv;
		this.rv.addRegisterAction(new RegisterListener());
	}
	
	class RegisterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			User user = rv.getUser();
			try {
				Socket mySocket = new Socket(ServerTCPControl.gethost(),ServerTCPControl.serverPort);
				ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
				Message message = new Message(Setting.REQUEST_REGISTER, user, null, null);
				oos.writeObject(message);
				ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
				try {
					Object o = ois.readObject();
					if (o instanceof Message) {
						Message response = (Message) o;
						if (response.getObj().equals("Ok")) {
							int result = rv.showMessage("Congratualation! You have registered successfully!");
							if (result == JOptionPane.OK_OPTION) {
								rv.setVisible(false);
							}
						} else {
							rv.showMessage("Register fail! Please try again!");
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				oos.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
