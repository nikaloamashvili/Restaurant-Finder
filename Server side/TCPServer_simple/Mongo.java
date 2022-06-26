import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo {

	MongoCollection<Document> gradesCollection;
	
	public void connect()
	{

	MongoClientURI uri = new MongoClientURI(
			"mongodb://effi:oops1312@cluster0-shard-00-00-3jh7u.mongodb.net:27017,cluster0-shard-00-01-3jh7u.mongodb.net:27017,cluster0-shard-00-02-3jh7u.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("myMongoDBtest");

		gradesCollection = database.getCollection("myTable");
	
	}
	public void insert(String name , String email , String phone)
	{
		Document emp1 = new Document();
		emp1.put("name", name);
		emp1.put("email", email);
		emp1.put("phone", phone);
		
		gradesCollection.insertOne(emp1);
	}
	
}
