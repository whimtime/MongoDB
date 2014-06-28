package com.SocialWEB.classi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class CostruzioneHTML 
{

	
	
	
	private String prelevamentoPregresso(String utente) throws UnknownHostException
	{
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("SocialMongoDB");
		BasicDBObject query = new BasicDBObject("utente", utente);
		DBCollection coll = db.getCollection("commenti");
		DBCursor cursor = coll.find(query);
		String ListaCommenti = "";

		try {
		   while(cursor.hasNext()) {
		       //System.out.println(cursor.next());
		       
		       DBObject dbo = cursor.next();
		       String user = (String) dbo.get("utente");
		       Date dataInserimento = (Date) dbo.get("datainserimento");
		       String commento = (String) dbo.get("commento");
		       
		       ListaCommenti += this.getStrutturaPost(user,commento);
		       
		   }
		} finally {
		   cursor.close();
		   return ListaCommenti;
		}
		
	}
	
	private String getStrutturaPost(String username, String commento)
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dataOggi = new Date();
		
		String postSingolo = "<img src= \"images/photo.jpg\" height=\"32\" width=\"32\"/>" + commento.substring(0,10) + "...</span><strong>  -  " + df.format(dataOggi) + "</strong><p>" + commento + "</p>";			
		return postSingolo;
	}
	
	@SuppressWarnings("deprecation")
	private String getTemplate()
	{
		File doc2=new File("C:\\Users\\PortatileTiz\\git\\MongoDB\\SocialWEB\\WebContent\\index.html");
		URL path=null;
		String template="";
       
		    try
		    {
		      
		      path=doc2.toURL(); 
		      System.out.println("Il doc si trova nel percorso" + path);
		     
		      doc2=new File(path.getFile()); 
		      System.out.println("Nome del file " + doc2);
		      int i;
		        
		      InputStream is=path.openStream();
		      BufferedReader br=new BufferedReader(new InputStreamReader(is));
		
		      
		      do
		      {
		        i=br.read();
		        template += (char)i;		  
		      }
		      while (i!=-1);
		      is.close();
		    }					   
		    catch (MalformedURLException e)
		    {
		      System.out.println("Attenzione:" + e);
		    }
		    catch (IOException e)
		    {
		      System.out.println(e.getMessage());
		    } 
		    
		    	return template;
		    
		  }
	
	public String GetDocumentoFinale(String utente) throws UnknownHostException
	{
		String docHTML = this.getTemplate();
		String post = this.prelevamentoPregresso(utente);
		
		docHTML= docHTML.replace("VARIABILE_POST", post);
		return docHTML;
	}
	
	

}


