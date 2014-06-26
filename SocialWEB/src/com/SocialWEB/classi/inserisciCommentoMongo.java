package com.SocialWEB.classi;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class inserisciCommentoMongo 
{
	public boolean inserimentoCommentoMongoDB(Commento commento) throws UnknownHostException
	{
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("SocialMongoDB");
		
		DBCollection table = db.getCollection("commenti");
		BasicDBObject document = new BasicDBObject();
		document.put("utente", commento.getUtente());
		document.put("datainserimento", commento.getDataInserimento());
		document.put("commento", commento.getCommento());
		table.insert(document);
		
		return true;
	}
}
