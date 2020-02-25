package tools;
import java.sql.*;
import java.util.ArrayList;

public class FriendsTools {
	public static ArrayList<Integer> get_friends(int id, Connection c){
		String requete = "SELECT id2 FROM friends WHERE id1 = '"+id+"';";
		ArrayList<Integer> list_friends_id = new ArrayList<Integer>();
		try {
			Statement lecture = c.createStatement();
			ResultSet res = lecture.executeQuery(requete);
			while(res.next()) {	
				list_friends_id.add(res.getInt("id2"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list_friends_id;
	}
	
	public static boolean add_friend(int id1, int id2, Connection c){
		String requete = "INSERT INTO friends values('"+id1+"','"+id2+"');";
		try {
			Statement st = c.createStatement();
			if(st.executeUpdate(requete) == 1) {
				return true;
			}		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean remove_friend(int id1, int id2, Connection c){
		String requete = "DELETE FROM friends WHERE id1="+id1+" AND id2="+id2+";";
		try {
			Statement st = c.createStatement();
			if(st.executeUpdate(requete) == 1) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean is_friend(int id1, int id2, Connection c){
		try {
			String requete = "SELECT id1, id2 FROM friends WHERE id1 = "+id1+" AND id2="+id2+";";
			Statement lecture = c.createStatement();
			ResultSet res = lecture.executeQuery(requete);
			return res.next();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
