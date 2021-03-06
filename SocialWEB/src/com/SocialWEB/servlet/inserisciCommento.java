package com.SocialWEB.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SocialWEB.classi.Commento;
import com.SocialWEB.classi.CostruzioneHTML;
import com.SocialWEB.classi.Pagina;
import com.SocialWEB.classi.inserisciMongo;


/**
 * Servlet implementation class inserisciCommento
 */
@WebServlet("/inserisciCommento")
public class inserisciCommento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inserisciCommento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String comm = request.getParameter("paragraph_text");
		
		//inserimento commento singolo
		Commento commento = new Commento();
		commento.setCommento(comm);
		commento.setUtente("tiziano");
		commento.setDataInserimento(new Date());
		
		inserisciMongo insert = new inserisciMongo();
		insert.inserimentoCommentoMongoDB(commento);
		
		//rielaborazione pagina html statica
	/*	Pagina pagina= new Pagina();
		pagina.setDataGenerazione(new Date());
		pagina.setDocumentoHTML(new CostruzioneHTML().);*/
		
		
	}

}
