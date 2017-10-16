import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class package_attr extends Thread {
	   private Thread t;
	   private static Statement statement = null;
	   private static ResultSet dbResult = null;
	   private long transID;
	   private int src, dst, currentCity;
	   
	   private ArrayList<Integer> shortestPath = new ArrayList<Integer>();
	  // private String threadName;
	   
	   package_attr( long id, int source, int destination) {
	      this.transID = id;
	      this.src = source;
	      this.dst = destination;
	      this.currentCity = source;	     
	   }
	   
	   public void run() {
	     
	     // System.out.println("TransactionId = "+ this.transID);
	      
	      DBConnect db = new DBConnect();
			try {
				db.connectDataBase();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("Connected database successfully.");
			
			try {
				statement = db.connect.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
	      
	      for(int list : shortestPath) {
	    	  try {	    		     
	    		  
	  			String sqlQuery = "UPDATE Package SET currentCity =\""+FedExTracker.cities[list]+"\" WHERE Tracking_number = "+ this.transID;
	  			// Let the thread sleep for a while.
		        Thread.sleep(10000);
		        
		      	}catch (InterruptedException e) {
		      		System.out.println("Thread " +  this.transID + " interrupted.");
		      	}	    	  	    	  
	      }	
	      
	      String query = "UPDATE Package SET status = \"Delivered\" WHERE Tracking_number = "+ this.transID;
	      try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      db.close();
	     // System.out.println("Connection closed");
	      //System.out.println("Thread " +  this.transID + " exiting.");
	   }
	   
	   public void start () {
	     // System.out.println("Starting thread");
	      
	      Shortest_path city = new Shortest_path();				
	      shortestPath = city.getShortestPath(src, dst);
	      
	     /* for(int list : shortestPath) {
				System.out.println(FedExTracker.cities[list]);
	      }*/	      
	      if (t == null) {
	         t = new Thread (this);
	         t.start ();
	      }
	   }
	}