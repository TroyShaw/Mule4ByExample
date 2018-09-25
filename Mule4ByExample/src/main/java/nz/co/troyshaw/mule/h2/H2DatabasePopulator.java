package nz.co.troyshaw.mule.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class encapsulates creation of an in-memory H2 database.
 * 
 * The database contains a single table 'WORKERS' with test data, for the purpose
 * of exploring the Database connector in Mule.
 * 
 * See Database.xml configuration file to see how to interact with this database via Mule.
 * 
 * Note: it is not recommended to use the H2 database for production use.
 * It is used for demonstration purposes only.
 * 
 * @author troys
 */
public class H2DatabasePopulator {

	private static final String DATABASE_DRIVER = "org.h2.Driver";
	private static final String DATABASE_CONNECTION = "jdbc:h2:mem:test.db";
	
	// Note, don't store your credentials in your Java code. For demonstration purposes only.
	private static final String DATABASE_USER = "sa";
	private static final String DATABASE_PASSWORD = "YOURNEWPASSWORD";

	public H2DatabasePopulator() {
		Connection connection = getDBConnection();

		populate(connection);
	}

	private void populate(Connection connection) {
        String createSQLQuery = "CREATE TABLE WORKERS(employeeid int auto_increment primary key, "
                + "                                firstname varchar(100), "
                + "                                lastname varchar(100), "
                + "                                department varchar(100),"
                + "                                location varchar(100))";

		String insertUser1 = "INSERT INTO WORKERS (firstname, lastname, department, location) VALUES ('John', 'Smith', 'Software Development', 'Wellington')";
		String insertUser2 = "INSERT INTO WORKERS (firstname, lastname, department, location) VALUES ('Sally', 'Ger', 'Software Development', 'Auckland')";
		String insertUser3 = "INSERT INTO WORKERS (firstname, lastname, department, location) VALUES ('Nathan', 'Stallman', 'Software Development', 'Christchurch')";

		try {
			PreparedStatement createTablePreparedStatement = connection.prepareStatement(createSQLQuery);
			
			createTablePreparedStatement.execute();
			
			PreparedStatement insertUser1PreparedStatement = connection.prepareStatement(insertUser1);
			PreparedStatement insertUser2PreparedStatement = connection.prepareStatement(insertUser2);
			PreparedStatement insertUser3PreparedStatement = connection.prepareStatement(insertUser3);
			
			insertUser1PreparedStatement.execute();
			insertUser2PreparedStatement.execute();
			insertUser3PreparedStatement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection getDBConnection() {
		Connection H2DBConnection = null;

		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.toString());
		}
		
		try {
			H2DBConnection = DriverManager.getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);

			return H2DBConnection;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
