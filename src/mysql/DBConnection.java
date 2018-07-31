package mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnection {

    public static Connection getConnection() throws Exception {
    	Connection connect = null;
    	String url = "jdbc:mysql://localhost/305";
    	String user = "root";
    	String password = "shinobi";
    	
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            //you have to replace the arguments to match your schema name, db username and password
            connect = DriverManager
                    .getConnection(url, user, password);
        }
        catch(SQLException e){ 
            e.printStackTrace();
        }
        return connect;		
    }
    
    public static void close(Connection con, ResultSet rs, Statement st){
    	try {
    			if(con!= null) con.close();
			if(rs != null) rs.close();
			if(st != null) st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}