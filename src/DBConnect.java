import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	public Connection connect = null;
    

    public void connectDataBase() throws Exception {
        try {
           
        	String myDriver = "org.gjt.mm.mysql.Driver";
    		String myUrl = "jdbc:mysql://127.0.0.1:3306/FedEx";
    		Class.forName(myDriver);
    		//Connection conn = null;
    		connect = DriverManager.getConnection(myUrl, "root", "zing77");
       
        
        } catch (Exception e) {
            throw e;
        } 

    }
    
    public void close() {
        try {     

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
