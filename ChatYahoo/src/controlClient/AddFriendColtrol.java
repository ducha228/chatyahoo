package controlClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Message;
import model.Setting;
import view.AddFriend;

public class AddFriendColtrol {

	private AddFriend view;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public AddFriendColtrol(final AddFriend view, ObjectInputStream ois,
			ObjectOutputStream oos) {
		super();
		this.view = view;
		this.ois = ois;
		this.oos = oos;
		view.addFriendAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sendMessage(new Message(Setting.REQUEST_ADDFRIEND, view.getUserNameFriend(), view.getUser().getUserName(), view.getUser().getUserName()));
				view.setVisible(false);
			}
		});
	}

	public void sendMessage(Message ms) {
		try {
			oos.writeObject(ms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
