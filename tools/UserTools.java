package tools;

import java.sql.*;

public class UserTools {
	public static boolean UserLoginExists(String login, Connection c){
		try {
			String requete = "SELECT login FROM user WHERE login = '"+login+"';";
			Statement lecture = c.createStatement();
			ResultSet res = lecture.executeQuery(requete);
			return res.next();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public static int UserLoginCheck(String login, String mdp, Connection c){
		try {
			String requete = "SELECT id FROM user WHERE login = '"+login+"' AND mdp = '"+mdp+"';";
			Statement lecture = c.createStatement();
			ResultSet res = lecture.executeQuery(requete);
			if(res.next()) {
				return res.getInt("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static boolean UserExists(int id, Connection c){
		try {
			String requete = "SELECT login FROM user WHERE id = '"+id+"';";
			Statement lecture = c.createStatement();
			ResultSet res = lecture.executeQuery(requete);
			return res.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean insertUser(String login, String mdp, String pseudo, Connection c){
		try {
			String requete = "INSERT INTO user values('"+login+"','"+mdp+"','"+pseudo+"',NULL);";
			Statement st = c.createStatement();
			if(st.executeUpdate(requete) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
