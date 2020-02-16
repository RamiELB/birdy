package tools;
import java.sql.*;
import java.util.ArrayList;

public class FriendsTools {
	public static ArrayList<Integer> get_friends(int id, Connection c) throws SQLException {
		String requete = "SELECT id2 FROM friends WHERE id1 = '"+id+"';";
		Statement lecture = c.createStatement();
		ResultSet res = lecture.executeQuery(requete);
		ArrayList<Integer> list_friends_id = new ArrayList<Integer>();
		while(res.next()) {	
			list_friends_id.add(res.getInt("id1"));
		}
		return list_friends_id;
	}
	
	public static boolean add_friend(int id1, int id2, Connection c) throws SQLException {
		String requete = "INSERT INTO friends values('"+id1+"','"+id2+"');";
		Statement st = c.createStatement();
		if(st.executeUpdate(requete) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean remove_friend(int id1, int id2, Connection c) throws SQLException {
		String requete = "DELETE FROM friends WHERE id1="+id1+" AND id2="+id2+";";
		Statement st = c.createStatement();
		if(st.executeUpdate(requete) == 1) {
			return true;
		}
		return false;
	}
}
