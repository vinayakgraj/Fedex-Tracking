import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class query extends Thread{
	private Thread t;
	   private static Statement statement = null;
	   private static ResultSet dbResult = null;
	   private long transID;
	   private int src, dst, currentCity;
	   
	   String source = null;
		String destination = null;
		String curCity = null;
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<String> currentCityPath = new ArrayList<String>();
				
	   query(long id){
		   this.transID = id;
	   }
	   
	   public void initiate() throws Exception {
		        
		      if (t == null) {
		         t = new Thread (this);
		         t.start ();
		      }
		   }
	   
	   public void run() {
		     
		     // System.out.println("TransactionId = "+ this.transID);
		      
		   
			DBConnect dbConn = new DBConnect();
			try {
				dbConn.connectDataBase();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			System.out.println("Connected database successfully.");
			try{
			statement = dbConn.connect.createStatement();
			
			String sqlQuery = "Select * from Package where Tracking_number =" + this.transID;
			
			dbResult = statement.executeQuery(sqlQuery);
			while(dbResult.next()) {
				source = dbResult.getString(3);
				destination = dbResult.getString(4);
				curCity = dbResult.getString(5);
				String track_package = "Ship Date : "+ dbResult.getTimestamp(2)+"\n\nSource : %s\t\t\t\tDestination = %s", source, destination ;
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			int sourceCity = 0, destinationCity=0, presentCity=0;
			
			for(int i=0; i<25 ; i++) {
				if(FedExTracker.cities[i].equals(source)) {
					sourceCity = i;
				}
				if(FedExTracker.cities[i].equals(destination)) {
					destinationCity = i;
				}
				if(FedExTracker.cities[i].equals(curCity)) {
					presentCity = i;
				}
			}		
			
			Shortest_path cityPath = new Shortest_path();				
			path = cityPath.getShortestPath(sourceCity, destinationCity);
			
			for(int list : path) {
				if(list == presentCity) {
					currentCityPath.add(FedExTracker.cities[list]);					
					break;
				}
				currentCityPath.add(FedExTracker.cities[list]);
				
			}
			

			
			for(int i=0 ; i<currentCityPath.size();i++) {
				System.out.print(currentCityPath.get(i).toString());
				if(i != currentCityPath.size()-1)
					System.out.print(" --> ");
			}
			
			
			dbConn.close();
		     // System.out.println("Connection closed");
		      //System.out.println("Thread " +  this.transID + " exiting.");
		   }
}
