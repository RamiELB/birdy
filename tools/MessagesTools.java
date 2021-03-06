package tools;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Date;

public class MessagesTools {
	public static void send_msg(int id_user, String idPere, String msg, MongoDatabase db) {
		MongoCollection<Document> collectionMessages = db.getCollection("messages");
		Document row = new Document();
		row.append("msg", msg);
		row.append("auteur", id_user);
		if(!(idPere.equals("-1"))) {
			row.append("idPere", idPere);
		}
		row.append("date", new Date());
		collectionMessages.insertOne(row);
	}
	
	public static JSONObject get_msg_user(int id_user, MongoDatabase db){
		MongoCollection<Document> collectionMessages = db.getCollection("messages");
		Document filter = new Document();
		filter.append("auteur", id_user);
		FindIterable <Document> docs = collectionMessages.find(filter);
		JSONObject res = new JSONObject();
		JSONObject tmp;
		int num_msg = 0;
		ObjectId oid = new ObjectId();
		for(Document doc : docs) {
			tmp = new JSONObject();
			oid = (ObjectId) doc.get("_id");
			try {
				tmp.put("_id", oid.toHexString());
				tmp.put("id_auteur", doc.get("auteur"));
				tmp.put("idPere", doc.get("idPere"));
				tmp.put("msg", doc.get("msg"));
				res.put(Integer.toString(num_msg++), tmp);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static JSONObject get_replies(String idPere, MongoDatabase db){
		MongoCollection<Document> collectionMessages = db.getCollection("messages");
		Document filter = new Document();
		filter.append("_id", idPere);
		FindIterable <Document> docs = collectionMessages.find(filter);
		JSONObject res = new JSONObject();
		JSONObject tmp = new JSONObject();
		for(Document doc : docs) {
			ObjectId oid = (ObjectId) doc.get("_id");
			try {
				tmp.put("_id", oid.toHexString());
				tmp.put("msg", doc.get("msg"));
				tmp.put("idPere", doc.get("idPere"));
				res.put("field", tmp);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static void del_msg(String id_msg, MongoDatabase db) {
		MongoCollection<Document> collectionMessages = db.getCollection("messages");
		ObjectId test = new ObjectId(id_msg);
		Document filter = new Document();
		filter.append("_id", test);
		collectionMessages.deleteOne(filter);
	}
}
