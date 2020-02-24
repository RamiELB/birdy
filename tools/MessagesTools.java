package tools;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Date;
import java.util.ArrayList;

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
	
	public static ArrayList<String> get_msg_user(int id_user, MongoDatabase db) {
		MongoCollection<Document> collectionMessages = db.getCollection("messages");
		Document filter = new Document();
		filter.append("auteur", id_user);
		FindIterable <Document> docs = collectionMessages.find(filter);
		ArrayList <String> res = new ArrayList <String>();
		for(Document doc : docs) {
			res.add(doc.toJson());
		}
		return res;
	}
	
	public static ArrayList<String> get_replies(String idPere, MongoDatabase db) {
		MongoCollection<Document> collectionMessages = db.getCollection("messages");
		Document filter = new Document();
		filter.append("_id", idPere);
		FindIterable <Document> docs = collectionMessages.find(filter);
		ArrayList <String> res = new ArrayList <String>();
		for(Document doc : docs) {
			res.add(doc.toJson());
		}
		return res;
	}
}
