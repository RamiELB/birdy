package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;


import javax.naming.InitialContext;
import javax.naming.NamingException;
// class en charge de la connexion aux base de données
public class Database {
	
	private DataSource dataSource;
	private static Database database = null;
	public Database(String ressource_name) throws SQLException{
		try {
			// construire l'objet dataSource à partir du fichier de context
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/"+ressource_name);
		}catch (NamingException e) {
			throw new SQLException(ressource_name+" unreachable :"+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	public static Connection getMySQLConnection(){
		// si on n'utilise pas le pooling 
		if(!DBStatic.pooling) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// on créer donc une nouvelle connexion
			try {
				return DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db, DBStatic.mysql_user, DBStatic.mysql_password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			/* on notera toutefois que le port est nécéssaire si la bd n'est pas interfacer au port par défaut.
			 *  Ainsi jdbc:mysql://"+DBStatic.mysql_host+":"+DBStatic.mysql_port+"/"+DBStatic.mysql_bd
			 */
		}
		// si on utilise le pooling
		else {
			// si c'est la toute première connexion
			if(database == null) {
				/* on creer l'objet Database en indiquant le nom de la 
				 * ressource dans le fichier de context
				 */
				try {
					database = new Database("jdbc/db");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// on retourne la connexion du pooling
			try {
				return database.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	public static MongoDatabase getMongoDBConnection()  {
		MongoClient mongo = MongoClients.create();
		return mongo.getDatabase(DBStatic.mongo_bd);
	}

}