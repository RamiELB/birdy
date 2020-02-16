package services;
import org.json.JSONException;
import org.json.JSONObject;
import bd.Database;
import tools.ErrorJSON;
import tools.FriendsTools;
import tools.UserTools;

import java.util.ArrayList;
import java.sql.*;

public class Friends {
	
	public static JSONObject add_friend(int id1, int id2) throws SQLException, JSONException {
		Connection c = Database.getMySQLConnection();
		if(!(UserTools.UserExists(id1, c))) {
			return ErrorJSON.serviceRefused("Le premier utilisateur n'existe pas", -1);	
		}
		if(!(UserTools.UserExists(id2, c))) {
			return ErrorJSON.serviceRefused("Le second utilisateur n'existe pas", -1);			
		}
		
		if(FriendsTools.add_friend(id1, id2, c)){
			c.close();
			return tools.ErrorJSON.serviceAccepted();
		}
		c.close();
		return tools.ErrorJSON.serviceRefused("Erreur SQL", -1);
	}
	
	public static JSONObject get_friends(int id) throws SQLException, JSONException {
		Connection c = Database.getMySQLConnection();
		if(!(UserTools.UserExists(id, c))) {
			return ErrorJSON.serviceRefused("L'utilisateur n'existe pas", -1);	
		}
		ArrayList<Integer> list_id = FriendsTools.get_friends(id, c);
		c.close();
		JSONObject list_friends = new JSONObject();
		for(int i : list_id) {
			list_friends.put("id", i);
		}
		return list_friends;
	}
	
	public static JSONObject delete_friend(int id1, int id2) throws SQLException, JSONException {
		Connection c = Database.getMySQLConnection();
		if(FriendsTools.remove_friend(id1, id2, c)) {
			c.close();
			return ErrorJSON.serviceAccepted();
		}
		c.close();
		return ErrorJSON.serviceRefused("Erreur SQL", -1);
	}
}
