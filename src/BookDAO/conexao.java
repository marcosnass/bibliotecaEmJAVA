package BookDAO;
import java.sql.*;

public class conexao {
	public static Connection conector() {
		java.sql.Connection con = null;
		String driver = "org.postgresql.Driver";
		String url="jdbc:postgresql://localhost:5432/Libary";
		String user = "postgres";
		String password = "root";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
