package tools;

import java.sql.*;
import org.json.JSONException;

public class UserTools {
	public static boolean UserLoginExists(String login, Connection c) throws SQLException {
		String requete = "SELECT login FROM user WHERE login = '"+login+"';";
		Statement lecture = c.createStatement();
		ResultSet res = lecture.executeQuery(requete);
		return res.next();
	}
	
	public static boolean UserExists(int id, Connection c) throws SQLException {
		String requete = "SELECT login FROM user WHERE id = '"+id+"';";
		Statement lecture = c.createStatement();
		ResultSet res = lecture.executeQuery(requete);
		return res.next();
	}
	
	public static boolean insertUser(String login, String mdp, String pseudo, Connection c) throws SQLException, JSONException {
		String requete = "INSERT INTO user values('"+login+"','"+mdp+"','"+pseudo+"',NULL);";
		Statement st = c.createStatement();
		if(st.executeUpdate(requete) == 1) {
			return true;
		}
		return false;
	}
}
