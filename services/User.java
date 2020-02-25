package services;

import org.json.JSONObject;

import com.mongodb.client.MongoDatabase;
import bd.Database;
import tools.SessionTools;
import tools.UserTools;
import java.sql.*;

public class User {
	
	public static JSONObject login(String login, String mdp){
		try {
			if( (login == null) || (mdp == null)) {
				return tools.ErrorJSON.serviceRefused("Wrong Arguments", -1);
			}
			
			Connection c = Database.getMySQLConnection();
			
			int id = tools.UserTools.UserLoginCheck(login, mdp, c);
			if( id == -1){
				return tools.ErrorJSON.serviceRefused("User does not exist", -1);
			}	
			c.close();
			
			MongoDatabase db = Database.getMongoDBConnection();
			SessionTools.create_session(id, db);
			
			return tools.ErrorJSON.serviceAccepted();		
		}catch(SQLException e) {
			e.printStackTrace();
			return tools.ErrorJSON.serviceRefused("User does not exist", -1);
		}

	}
	
	public static JSONObject logout(String key){
		MongoDatabase db = Database.getMongoDBConnection();
		SessionTools.del_session(key, db);;
		return tools.ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject createUser(String login, String mdp, String pseudo){
		if( (login == null) || (mdp == null) || (pseudo == null) || (login.length() < 3) || (mdp.length() < 3) || (pseudo.length() < 3)) {
			return tools.ErrorJSON.serviceRefused("Wrong Arguments", -1);
		}
		
		Connection c = Database.getMySQLConnection();
		
		if((tools.UserTools.UserLoginExists(login, c))){
			return tools.ErrorJSON.serviceRefused("User exists already", -1);
		}	
		
		if(UserTools.insertUser(login, mdp, pseudo, c)) {
			return tools.ErrorJSON.serviceAccepted();
		}
		return tools.ErrorJSON.serviceRefused("DB Error", 1000);
	}
}
