package services;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import com.mongodb.client.MongoDatabase;
import bd.Database;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Messages {
	public static JSONObject send_msg(int id_user, String idPere, String msg) throws JSONException {
		MongoDatabase db = Database.getMongoDBConnection();
		tools.MessagesTools.send_msg(id_user, idPere, msg, db);
		return tools.ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject get_msg_user(int id_user) throws JSONException, ParseException {
		MongoDatabase db = Database.getMongoDBConnection();
		ArrayList<String> list_msg = tools.MessagesTools.get_msg_user(id_user, db);
		JSONObject list_msg_json = new JSONObject();
		JSONParser parser = new JSONParser();
		for(String msg : list_msg) {
			list_msg_json.put("msg", (JSONObject) parser.parse(msg));
		}
		return list_msg_json;
	}
	
	public static JSONObject get_replies(String idPere) throws JSONException, ParseException {
		MongoDatabase db = Database.getMongoDBConnection();
		ArrayList<String> list_msg = tools.MessagesTools.get_replies(idPere, db);
		JSONObject list_msg_json = new JSONObject();
		JSONParser parser = new JSONParser();
		for(String msg : list_msg) {
			list_msg_json.put("msg", (JSONObject) parser.parse(msg));
		}
		return list_msg_json;
	}
}
