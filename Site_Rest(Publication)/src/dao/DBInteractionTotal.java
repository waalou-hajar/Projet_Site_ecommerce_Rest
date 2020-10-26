package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Article;
import model.Commande;
import model.User;
import model.panier;

@Path("/traitement")
public class DBInteractionTotal {



	/*****************
	 * Ajouter un document
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 * @throws SQLException
	 ************/
//	bien fait
	@POST
	@Path("/AjouterLivre")
	@Produces(MediaType.APPLICATION_JSON)
	public  void AddArticle(Article a) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		String query = " INSERT INTO article(des,prix,qte,img) values(?,?,?,?)";
		try  {
			
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
//			String desc, double prix, int qte, String img
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, a.getDesc());
			pr.setDouble(2, a.getPrix());
			pr.setInt(3, a.getQte());
			pr.setString(4, a.getImg());
			pr.execute();
			System.out.println("Articel est ajoute");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	/***************
	 * Recherche document
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 ***********/
//	bien fait
	@GET
	@Path("/ChercherLivre/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Article FindArticle( @PathParam("id")int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		Article l = new Article();
		try {
			String sql = "select * from article where id=?";
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = (PreparedStatement) con.prepareStatement(sql);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				l.setDesc(rs.getString("des"));
				l.setId(rs.getInt("id"));
				l.setImg(rs.getString("img"));
				l.setPrix(rs.getDouble("prix"));
				l.setQte(rs.getInt("qte"));
				
			}
			pr.close();
			return l;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/************
	 * Supprimer document
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *************/
	@DELETE
	@Path("/SupprimerArticle/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public  void deleteArticle(@PathParam("id")int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String sql = "delete from article where id=?";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = (PreparedStatement) con.prepareStatement(sql);
			pr.setInt(1, id);
			pr.execute();
			System.out.println("Article est supprime");
		}catch(SQLException e) {
			e.printStackTrace();
		}		
}
	
	@DELETE
	@Path("/SupprimerCommande/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public  void SupCmd(@PathParam("id")int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String sql = "delete from article where idc=?";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = (PreparedStatement) con.prepareStatement(sql);
			pr.setInt(1, id);
			pr.execute();
			System.out.println("commande est supprime");
		}catch(SQLException e) {
			e.printStackTrace();
		}		
}
	
/*  public static void supprimerlignecommande(int ID)
        {
            string query="delete from commande where id="+ID;
            MySqlConnection connection=DAO.getconnectionDAO();
            MySqlCommand command=new MySqlCommand(query,connection);
            command.ExecuteNonQuery();
            Console.WriteLine("its worked");
            connection.Close();
        */
	/**************
	 * modifier Dociment
	 ************/
//	@WebMethod
//	public int UpdateArticle(int id, Article a) {
//
//		
//		String sql = "update users set 'des'='" + a.getDesc() + "' ,'prix'='" + a.getPrix() + "','qte'='" + a.getQte()
//				+ "','email'='" + a.getImg() + "' where 'id'=" + id;
//		int nb = Maj(sql);
//		disconnect();
//		return nb;
//	}

	/*************
	 * Consulter Document
	 *************/
//	bien fait
	@GET
	@Path("/livres")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Article> AllArticle() {
		ArrayList<Article> ps = new ArrayList<>();
		
		String sql = "select * from article ";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
		PreparedStatement pr = con.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();
		while (rs.next()) {
			Article l=new Article();
			l.setId(rs.getInt("id"));
			l.setDesc(rs.getString("des"));
			l.setImg(rs.getString("img"));
			l.setPrix(rs.getDouble("prix"));
			l.setQte(rs.getInt("qte"));
			ps.add(l);
		}} catch (Exception e) {
			e.printStackTrace();
			}
		return ps;
	}
	

	/*************
	 * Consulter Document
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *************/
// bien fait
	@POST
	@Path("/Ajoutercommande")
	@Produces(MediaType.APPLICATION_JSON)
	public void ajoutercommande(Commande a) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	
		String query = "insert into comm(nomc,prixc) values(?,?)";
		
		try  {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, a.getNom_client());
			pr.setDouble(2, a.getPrixTotale());
			pr.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	bien fait
	@GET
	@Path("/Affichercommande")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Commande> AllCommande() {

		ArrayList<Commande> ps = new ArrayList<>();
		
		String sql = "select * from comm";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
		PreparedStatement pr = con.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();
		while (rs.next()) {
			Commande l=new Commande();
			l.setIdCommande(rs.getInt("idc"));
			l.setNom_client(rs.getString("nomc"));
			l.setPrixTotale(rs.getDouble("prixc"));
			ps.add(l);
		}} catch (Exception e) {
			e.printStackTrace();
			}
		return ps;

		

	}

	/******************
	 * panier
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *******************/

	// Ajouter au panier bien fait
	@POST
	@Path("/AjouterlivrePanier")
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouterLivre(panier a) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		String sql = "insert into panier(nomlivre,prixlivre) values(?,?)";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setString(1, a.getNom_livre()); 
			 pr.setDouble(2,a.getPrix_livre());
			 pr.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	bien fait	
	@DELETE
	@Path("/ViderPanier")
	@Produces(MediaType.APPLICATION_JSON)
	public void vider() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		String sql = "TRUNCATE TABLE panier";
		try  {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = con.prepareStatement(sql);
			pr.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

//	bien fait
	@GET
	@Path("livresPanier")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<panier> Alllivres() {
		ArrayList<panier> ps = new ArrayList<>();
		
		String sql = "select * from panier ";
		
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
		PreparedStatement pr = con.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();
		while (rs.next()) {
			panier l=new panier();
			l.setId(rs.getInt("id"));
			l.setNom_livre(rs.getString("nomlivre"));
			l.setPrix_livre(rs.getDouble("prixlivre"));
			ps.add(l);
		}} catch (Exception e) {
			e.printStackTrace();
			}
		return ps;
		

	}

	@DELETE
	@Path("/SupprimerlivreP/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deletelivre(@PathParam("id")int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		String sql = "delete from panier where id=?";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = (PreparedStatement) con.prepareStatement(sql);
			pr.setInt(1, id);
			pr.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/* ****UderDoa********* */

	/********* ajouter user 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException *********/
//	bien fait
	@POST
	@Path("/AjouterUser")
	@Produces(MediaType.APPLICATION_JSON)
	public void AddUserW_A(User a) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		
		String sql = "insert into user(nom,login,password,email) values(?,?,?,?)";
		try  {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setString(1, a.getNom());
			 pr.setString(2, a.getLogin()); 
			 pr.setString(3,a.getPassword());
			 pr.setString(4,a.getEmail());
			 pr.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	

	}

	/********* ajouter parametre ********/
//	@WebMethod
//	public int AddUser(String nom, String login, String password, String email) {
//		connect();
//		String sql = "insert into user(nom,login,password,email) values('" + nom + "','" + login + "','" + password
//				+ "','" + email + "')";
//		int n = Maj(sql);
//		disconnect();
//		return n;
//	}

	/********** Recherche user 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException *********/
//	bien fait
	@GET
	@Path("/ChercherUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User FindUser(@PathParam("id")int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		User l = new User();
		
		String sql = "select * from user where id=?";
	
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = (PreparedStatement) con.prepareStatement(sql);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				
				l.setId(rs.getInt("id"));
				l.setNom(rs.getString("nom"));
				l.setEmail(rs.getString("email"));
				l.setLogin(rs.getString("login"));
				l.setPassword(rs.getString("password"));	
			}
			pr.close();
			return l;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	
	
	/************* Supprimer user 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException *********/
//	bien fait
	@DELETE
	@Path("/SupprimerUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteUser(@PathParam("id")int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		String sql = "delete from user where id=?";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
			PreparedStatement pr = (PreparedStatement) con.prepareStatement(sql);
			pr.setInt(1, id);
			pr.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/************* Modifier user ********/
//	@WebMethod
//	public int UpdateUser(int id, User u) {
//		connect();
//		String sql = "update users set nom='" + u.getNom() + "' ,login='" + u.getLogin() + "', password='"
//				+ u.getPassword() + "',email='" + u.getEmail() + "' where id=" + id;
//		int nb = Maj(sql);
//		disconnect();
//		return nb;
//	}

	/************* Consulter ********/
//	bien fait
	@GET
	@Path("/utilisateurs")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> AllUser() {
		ArrayList<User> ps = new ArrayList<>();
		String sql = "select * from user ";
		try {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
		
	//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308", "root", "");
		PreparedStatement pr = con.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();
		while (rs.next()) {
			User l=new User();
			l.setId(rs.getInt("id"));
			l.setNom(rs.getString("nom"));
			l.setEmail(rs.getString("email"));
			l.setLogin(rs.getString("login"));
			l.setPassword(rs.getString("password"));
			ps.add(l);
		}} catch (Exception e) {
			e.printStackTrace();
			}
		return ps;

		
	}

	/************
	 * Authentification
	 ************/
//	@WebMethod
//	public Admin AuthAdmin(String nom, String email) {
//		Admin u = null;
//		connect();
//		String sql = "select * from Admin where nomAdmin='" + nom + "' and emailAdmin='" + email + "'";
//		ResultSet rs = Select(sql);
//
//		try {
//			if (rs.next()) {
//				u = new Admin(rs.getString(2), rs.getString(3));
//				u.setId(rs.getInt(1));
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//
//		disconnect();
//		return u;
//	}

//	@WebMethod
//	public User Auth(String nom, String email) {
//		User u = null;
//		connect();
//		String sql = "select * from user where nom='" + nom + "' and email='" + email + "'";
//		ResultSet rs = Select(sql);
//
//		try {
//			if (rs.next()) {
//				u = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
//				u.setId(rs.getInt(1));
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//
//		disconnect();
//		return u;
//	}
	
// bien fait	
	@GET
	@Path("/ValiderUser/{email}/{nom}")
	@Produces(MediaType.APPLICATION_JSON)
	public  User isValidLogin(@PathParam("email") String email, @PathParam("nom")  String nom) {
		try  {
			String url = "jdbc:mysql://localhost:3308/ecommerce";
			String driver ="com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection con;
			con = DriverManager.getConnection(url, "root", "");
		
			String strSql = "SELECT * FROM user WHERE email=? AND nom=?";
			try (PreparedStatement statement = con.prepareStatement(strSql)) {
				statement.setString(1, email);
				statement.setString(2, nom);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						return new User (resultSet.getString("nom"), resultSet.getString("login"),
								resultSet.getString("password"), resultSet.getString("email")
								);
//						String nom, String login, String password, String email
					} else {
						return null;
					}
				}
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

	}

}