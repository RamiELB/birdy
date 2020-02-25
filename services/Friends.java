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
	
	public static JSONObject add_friend(int id1, int id2){
		Connection c = Database.getMySQLConnection();
		if(!(UserTools.UserExists(id1, c))) {
			return ErrorJSON.serviceRefused("Le premier utilisateur n'existe pas", -1);	
		}
		if(!(UserTools.UserExists(id2, c))) {
			return ErrorJSON.serviceRefused("Le second utilisateur n'existe pas", -1);			
		}
		if(FriendsTools.is_friend(id1, id2, c)) {
			return tools.ErrorJSON.serviceAccepted();
		}
		if(FriendsTools.add_friend(id1, id2, c)){
			return tools.ErrorJSON.serviceAccepted();
		}
		return tools.ErrorJSON.serviceRefused("Erreur SQL", 1000);
	}
	
	public static JSONObject get_friends(int id){
		Connection c = Database.getMySQLConnection();
		if(!(UserTools.UserExists(id, c))) {
			return ErrorJSON.serviceRefused("L'utilisateur n'existe pas", -1);	
		}
		ArrayList<Integer> list_id = FriendsTools.get_friends(id, c);
		JSONObject list_friends = new JSONObject();
		try {
			list_friends.put("amis", list_id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list_friends;
	}
	
	public static JSONObject delete_friend(int id1, int id2){
		Connection c = Database.getMySQLConnection();
		if(FriendsTools.remove_friend(id1, id2, c)) {
			return ErrorJSON.serviceAccepted();
		}
		return ErrorJSON.serviceRefused("Erreur SQL", 1000);
	}
}
