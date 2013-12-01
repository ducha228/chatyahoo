package controlClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.SmileIcon;

public class SplitString {

	

	public SplitString() {
		// TODO Auto-generated constructor stub

	}

//	public static Vector<String> vecSign() {
//		Connection conn = DBConnection.getConn();
//		String sql = "select * from tblsmileicon";
//		Vector<String> vec = new Vector<>();
//		try {
//			PreparedStatement pre = conn.prepareStatement(sql);
//
//			ResultSet rs = pre.executeQuery();
//
//			while (rs.next()) {
//				vec.add(rs.getString(3));
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return vec;
//
//	}

	public static Vector<String> splitString(Vector<SmileIcon> vecSign,String s) {
		Vector<String> vec = new Vector<>();
		Vector<SmileIcon> vecicon = vecSign;
		System.out.println(vecicon.size());
		int i = 0, j = 0;
		while (i < s.length()) {
			j = 0;
			while (j < vecicon.size()) {
				SmileIcon iconSmile = vecicon.get(j);
				if (s.startsWith(iconSmile.getShortKey(), i)) {
					vec.add(iconSmile.getShortKey());
					i = i + iconSmile.getShortKey().length();
					if (i == s.length() - 1) {
						break;
					}
				} else {
					j++;
				}
			}
			if (i < s.length())
				vec.add(String.valueOf(s.charAt(i)));
			i++;
		}
		return vec;
	}

//	public static void main(String[] args) {
//		String s = ":32_1:sac:32_10:";
//		Vector<String> vec = new SplitString().splitString(s);
//		for (String string : vec) {
//			System.out.println(string);
//		}
//	}
}
