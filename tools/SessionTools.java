package tools;

import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SessionTools {
	public static void create_session(int id_user, MongoDatabase db) {
		MongoCollection<Document> collectionSessions = db.getCollection("sessions");
		Document row = new Document();
		String key = RandomStringUtils.randomAlphanumeric(32);
		row.append("id_user", id_user);
		row.append("key", key);
		row.append("date", new Date());
		collectionSessions.insertOne(row);
	}
	
	public static void del_session(String key, MongoDatabase db) {
		MongoCollection<Document> collectionSessions = db.getCollection("sessions");
		Document filter = new Document();
		filter.append("key", key);
		collectionSessions.deleteOne(filter);
	}
	
	public static void update_session(String key, MongoDatabase db) {
		MongoCollection<Document> collectionSessions = db.getCollection("sessions");
		Document filter = new Document();
		filter.append("key", key);
		collectionSessions.updateOne(filter, new Document("date", new Date()));
	}
}
