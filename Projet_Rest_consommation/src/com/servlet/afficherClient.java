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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Commande;
import com.model.User;


@WebServlet("/afficherClient")
public class afficherClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public afficherClient() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Client client = javax.ws.rs.client.ClientBuilder.newClient();
		WebTarget targeto = client.target("http://localhost:8080/Site_Rest(Publication)/traitement/utilisateurs");
		String str=targeto.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(str);
		
		List<User> listeUser = mapper.readValue(str, new TypeReference<List<User>>(){});
		
		for (User user : listeUser) {
			System.out.println(user.getEmail());
		}
		request.setAttribute("listeUser", listeUser);
		
    	request.getRequestDispatcher("les_clients.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
