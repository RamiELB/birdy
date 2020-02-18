package test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


import bd.Database;

public class TestMongo {
	public static void main(String [] args) {
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> coll = db.getCollection("test");
		Document doc = new Document();
		doc.append("maxime", 20);
		doc.append("philou", 32);
		coll.insertOne(doc);
		Document filter = new Document();
		filter.append("philou", 32);
		Document test = coll.find(filter).first();
		System.out.println(test);
	}
}
