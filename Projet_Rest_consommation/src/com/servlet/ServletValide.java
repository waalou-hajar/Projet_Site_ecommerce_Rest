package com.servlet;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.panier;

@WebServlet("/ServletValide")
public class ServletValide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletValide() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = javax.ws.rs.client.ClientBuilder.newClient();
		WebTarget targeto = client.target("http://localhost:8080/Site_Rest(Publication)/traitement/livresPanier");
		String str=targeto.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
		ObjectMapper mapper = new ObjectMapper();
		
		FileInputStream fileInputStream1=null;
		try {
			FileWriter file = new FileWriter("json1.json");
			file.write(str);
			file.close();
			fileInputStream1 = new FileInputStream("json1.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		double s=0;
        String l="";
		List<panier> listePanier = mapper.readValue(fileInputStream1, new TypeReference<List<panier>>(){});
		
		for (panier panier : listePanier) {
			s=s+panier.getPrix_livre();
			l=l+panier.getNom_livre()+"-";
		}
		
		
		System.out.println(s);
		System.out.println(l);
		request.setAttribute("Prix_totale", s);
		request.setAttribute("livres_totale", l);
		request.getRequestDispatcher("valider.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
