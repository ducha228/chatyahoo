package controlClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class SplitString {

	Connection conn;

	public SplitString() {
		// TODO Auto-generated constructor stub

	}

	public Vector<String> vecSign() {
		conn = DBConnection.getConn();
		String sql = "select * from tblsmileicon";
		Vector<String> vec = new Vector<>();
		try {
			PreparedStatement pre = conn.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				vec.add(rs.getString(3));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vec;

	}

	public Vector<String> splitString(String s) {
		Vector<String> vec = new Vector<>();
		Vector<String> vecicon = vecSign();
		System.out.println(vecicon.size());
		int i = 0, j = 0;
		while (i < s.length()) {
			j = 0;
			while (j < vecicon.size()) {
				String iconSmile = vecicon.get(j);
				if (s.startsWith(iconSmile, i)) {
					vec.add(iconSmile);
					i = i + iconSmile.length();
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

	public static void main(String[] args) {
		String s = ":32_1:sac:32_10:";
		Vector<String> vec = new SplitString().splitString(s);
		for (String string : vec) {
			System.out.println(string);
		}
	}
}
