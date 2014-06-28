package com.SocialWEB.classi;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class inserisciMongo 
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
		
		Pagina pagina = new Pagina();
		pagina.setDataGenerazione(new Date());
		pagina.setUtente(commento.getUtente());
		pagina.setDocumentoHTML(new CostruzioneHTML().GetDocumentoFinale(commento.getUtente()));
		this.inserimentoPaginaMongoDB(pagina);
				
		
		
		return true;
	}
	
	
	private boolean inserimentoPaginaMongoDB(Pagina pagina) throws UnknownHostException
	{
		//devo verificare che non esista già un documento per questo utente 
		
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("SocialMongoDB");
		
		DBCollection table = db.getCollection("pagine");
		BasicDBObject document = new BasicDBObject();
		document.put("utente", pagina.getUtente());
		document.put("datageneazione", pagina.getDataGenerazione());
		document.put("documentoHTML", pagina.getDocumentoHTML());
		
		table.insert(document);
		
		return true;
	}
	
	
}
