package services;

import org.json.JSONObject;
import com.mongodb.client.MongoDatabase;
import bd.Database;

public class Messages {
	public static JSONObject send_msg(int id_user, String idPere, String msg){
		MongoDatabase db = Database.getMongoDBConnection();
		tools.MessagesTools.send_msg(id_user, idPere, msg, db);
		return tools.ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject get_msg(int id_user, String idPere) {
		if(idPere.equals("-1")) {
			return get_msg_user(id_user);
		}
		return get_replies(idPere);
	}
	
	public static JSONObject get_msg_user(int id_user){
		MongoDatabase db = Database.getMongoDBConnection();
		return tools.MessagesTools.get_msg_user(id_user, db);
	}
	
	public static JSONObject get_replies(String idPere){
		MongoDatabase db = Database.getMongoDBConnection();
		return tools.MessagesTools.get_replies(idPere, db);
	}
	
	public static JSONObject del_msg(String id_msg){
		MongoDatabase db = Database.getMongoDBConnection();
		tools.MessagesTools.del_msg(id_msg, db);
		return tools.ErrorJSON.serviceAccepted();
	}
}
