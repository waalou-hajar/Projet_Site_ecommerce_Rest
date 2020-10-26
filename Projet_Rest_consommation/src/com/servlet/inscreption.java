package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;



@WebServlet("/inscreption")
public class inscreption extends HttpServlet {
	private static final long serialVersionUID = 1L;
       // la variable

    public inscreption() {
        super();
    }

    //************ init **********
public void init(ServletConfig config) throws ServletException {
	super.init();

		
	}
//**************** DE GET **********

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nom=request.getParameter("nom");
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		String email=request.getParameter("gmail");
		String op=request.getParameter("btn");
		
		System.out.println(nom+" "+login+" "+email);
		if(op.equals("Register"))
		{
			if(email!=null && nom!=null && login!=null && password!=null) {
				User u = new User();
				u.setNom(nom);
				u.setEmail(email);
				
				u.setLogin(login);
				u.setPassword(password);
				Jsonb jsonb = JsonbBuilder.create();
				String objetserialise_JSON = jsonb.toJson(u);
				// POST 
				try {
					URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/AjouterUser");
			        HttpURLConnection con  = (HttpURLConnection) url.openConnection();
			        con.setDoOutput(true);
			        con.setRequestMethod("POST");
			        con.setRequestProperty("Content-Type", "application/json");
		  	      con.setRequestProperty("Accept", "application/json");
			        OutputStream os = con.getOutputStream();
			        os.write(objetserialise_JSON.getBytes());
			        os.close();
			        os.flush();
			        System.out.println(objetserialise_JSON.getBytes());
					BufferedReader br = new BufferedReader(new InputStreamReader(
							(con.getInputStream())));
					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}
					con.disconnect();
				  } catch (MalformedURLException e) {
					e.printStackTrace();

				  } catch (IOException e) {

					e.printStackTrace();

				 }
			}
			
			 		/*HttpSession ses =request.getSession();
				ses.setAttribute("user", u);
				response.sendRedirect("bienvenu2");*/
			//response.sendRedirect("login");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			}
			
			}
		
	
		
	

	//************ DE POST ********************
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		
		
	}
}

