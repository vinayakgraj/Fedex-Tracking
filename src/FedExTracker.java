import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Vector;


public class FedExTracker {
	private static Statement statement = null;
    private static ResultSet resultSet = null; 

    private static long id =0;
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

    	java.util.Date today = new java.util.Date();
    	return new java.sql.Timestamp(today.getTime());

    }
    public static String[] cities = {"Northborough","Edison","Pittsburgh","Allentown","Martinsburg","Charlotte","Atlanta","Orlando","Memphis","Grove City","Indianapolis","Detroit","New Berlin","Minneapolis","St. Louis","Kansas","Dallas","Houston","Denver","Salt Lake City","Phoenix","Los Angeles","Chino","Sacramento","Seattle"};
    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		while(true) {
		
		System.out.println("Select the operation to be performed:");
		System.out.println("1. Initiate a new package delivery");
		System.out.println("2. Query the delivery status");
		System.out.println("3. Exit");
		Scanner scan = new Scanner(System.in);
		
		switch(scan.nextInt()) {
			case 1: 
				System.out.println("Select the Service required");
				System.out.println("1. FedEx Home Delivery");
				System.out.println("2. FedEx Pick Up");
				String service = null;
				switch(scan.nextInt()){
					case 1 :  service = "FedEx Home Delivery";
					break;
					case 2 : service = "FedEx Pick Up";
					break;
					default : System.err.println("Invalid value selected");
					break;
				}
				System.out.println("Select the source:");
				for(int i=1; i<=25 ; i++)
					System.out.printf("%d. %s\n",i, cities[i-1]);
				
				int src = scan.nextInt();
				if(src<1 && src>25 )
					System.err.println("Invalid city selected. Please try again later");
				
				System.out.println("Select the destination");
				for(int i=1; i<=25 ; i++)
					System.out.printf("%d. %s\n",i, cities[i-1]);
				
				int dst = scan.nextInt();
				if(dst<1 || dst>25 ) 
					System.err.println("Invalid city selected. Please try again later");							
				
				System.out.println("Enter the weight of the packeage in lbs");
				float weight = scan.nextFloat();
				
				System.out.println("Enter the dimensions:");
				String dimensions = scan.next();

				System.out.println("Enter the number of pieces");
				int no_of_pieces = scan.nextInt();
				
				System.out.println("Is signature service required");
				System.out.println("1. Direct Signature required");
				System.out.println("2. No Signature requird");
				String sigService = null;
				switch(scan.nextInt()){
					case 1 : sigService = "Direct Signature required";
					break;
					case 2 : sigService = "No Signature requird";
					break;
					default : System.err.println("Invalid value selected");
					break;
				}				
				
				System.out.println("Special handling section");
				System.out.println("1. Direct Signature required");
				System.out.println("2. No Signature requird");
				String specialService = null;
				switch(scan.nextInt()){
					case 1 : specialService = "Direct Signature required";
					break;
					case 2 : specialService = "No Signature requird";
					break;
					default : System.err.println("Invalid value selected");
					break;
				}				
				
				
				
				DBConnect db = new DBConnect();
				db.connectDataBase();
				
				System.out.println("Connected database successfully.");
				
				statement = db.connect.createStatement();
				try{
				String sqlQuery = "Select Tracking_number from Package";
				
				resultSet = statement.executeQuery(sqlQuery);
				
				while(resultSet.next()) {
					id = resultSet.getLong(1);
					id++;
					System.out.println(id);
				}
						
				  // create the mysql insert preparedstatement
					PreparedStatement preparedStmt = db.connect.prepareStatement("insert into Package values (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
				    preparedStmt.setLong (1, id);
				    preparedStmt.setTimestamp (2, getCurrentTimeStamp());
				    preparedStmt.setString   (3, cities[src-1]);
				    preparedStmt.setString   (4 , cities[dst-1]);
				    preparedStmt.setString   (5, cities[src-1]);
				    preparedStmt.setTimestamp (6, getCurrentTimeStamp());
				    preparedStmt.setString   (7, service);			
				    preparedStmt.setFloat   (8, weight);
				    preparedStmt.setString   (9, dimensions);
				    preparedStmt.setInt   (10, no_of_pieces);
				    preparedStmt.setString   (11, sigService);
				    preparedStmt.setString   (12, specialService);
				    preparedStmt.setString   (13, "package");
				    preparedStmt.setString   (14, "In Progress");

				      // execute the preparedstatement
				    preparedStmt.execute();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				db.close();								
				
				System.out.println("TransactionId = "+ id);
				
				package_attr packet = new package_attr(id, src-1, dst-1);
				packet.start();
				
				
			break;
			
			case 2:
				System.out.println("Enter the transactionId");
				GUI GUI = new  GUI();
			     GUI.run();
				long tId = scan.nextLong();

query packetQuery = new query(tId);
				packetQuery.initiate();
				
				break;
			case 3: System.exit(0);
				break;
			default : System.out.println("Invalid option selected");
				break;
		}
	}
	}

public static Vector<String> getTrackingDetails(Long trackingnumber){
		
	
	   Statement statement = null;
	   ResultSet dbResult = null;
	   long transID;
	   int src;
	   int dst;
	   int currentCity;
	   
	   String source = null;
		String destination = null;
		String curCity = null;
		Vector<String> trackingDetails= new Vector<String>();
		DBConnect dbConn = new DBConnect();
		try {
			dbConn.connectDataBase();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try{
		statement = dbConn.connect.createStatement();
		String sqlQuery = String.format("Select * from Package where Tracking_number= %d",trackingnumber); 
		
		dbResult = statement.executeQuery(sqlQuery);
		while(dbResult.next()) {
			source = dbResult.getString(3);
			destination = dbResult.getString(4);
			curCity = dbResult.getString(5);
		//	trackingDetails = "";
			String Rd = String.format("Ship Date : \n"+ dbResult.getTimestamp(2));
			trackingDetails.add(Rd);
			Rd= String.format("\n\nSource : %s       \t\t\t\tDestination = :%s", source, destination);
			trackingDetails.add(Rd);
			//Rd= String.format("");
			//trackingDetails.add(Rd);
			Rd= String.format("\nTransaction number : %d\t\t  Service :%s\n", trackingnumber, dbResult.getString(7));
			trackingDetails.add(Rd);
			Rd= String.format("Weight : %flbs\t\t\t\t  Dimensions :%s\n", dbResult.getFloat(8), dbResult.getString(9));
			trackingDetails.add(Rd);
			Rd= String.format("Signature Services : %s\t  Total pieces :%d\n", dbResult.getString(11), dbResult.getInt(10));
			trackingDetails.add(Rd);
			Rd= String.format("Packaging : %s\t\t\t     Special Handling Section :%s\n", dbResult.getString(13), dbResult.getString(12));
			trackingDetails.add(Rd);
			//Rd= String.format("");
			//trackingDetails.add(Rd);
			Rd= String.format("\nCurrent City : %s \t   Transaction Status : %s",dbResult.getString(5),  dbResult.getString(14));
			trackingDetails.add(Rd);

		
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return trackingDetails;
	}



}
