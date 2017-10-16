
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class GUI implements Runnable{
	   private Frame mainFrame;
	   private Label headerLabel;
	   private Label statusLabel;
	   private Label statusLabel2;
	   private Label statusLabel3;
	   private Label statusLabel4;
	   private Label statusLabel5;
	   private Label statusLabel6;
	   private Label statusLabel7;
	   private Panel controlPanel;
	   static Long Trackingnumber;

	   public GUI(){
	      prepareGUI();
	   }

	   private void prepareGUI(){
		      mainFrame = new Frame("Fedex Tracking System");
		      mainFrame.setSize(500,500);
		      mainFrame.setLayout(new GridLayout(10, 1));
		      mainFrame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            System.exit(0);
		         }        
		      });    
		      headerLabel = new Label();
		      headerLabel.setAlignment(Label.CENTER);
		      statusLabel = new Label(); 
		      statusLabel2 = new Label();
		      statusLabel3 = new Label();
		      statusLabel4 = new Label();
		      statusLabel5 = new Label();
		      statusLabel6 = new Label();
		      statusLabel7 = new Label();
		      statusLabel.setAlignment(Label.CENTER);
		   //   statusLabel.setSize(5,10);
		      statusLabel2.setAlignment(Label.CENTER);
		     // statusLabel2.setSize(5,10);
		      statusLabel3.setAlignment(Label.CENTER);
		      //statusLabel3.setSize(5,10);
		      statusLabel4.setAlignment(Label.CENTER);
		      //statusLabel4.setSize(5,10);
		      statusLabel5.setAlignment(Label.CENTER);
		      //statusLabel5.setSize(5,10);
		      statusLabel6.setAlignment(Label.CENTER);
		     // statusLabel6.setSize(5,10);
		      statusLabel7.setAlignment(Label.CENTER);
		      //statusLabel7.setSize(5,10);
		      
		      controlPanel = new Panel();
		      controlPanel.setLayout(new FlowLayout());

		      mainFrame.add(headerLabel);
		      mainFrame.add(controlPanel);
		      mainFrame.add(statusLabel);
		      mainFrame.add(statusLabel2);
		      mainFrame.add(statusLabel3);
		      mainFrame.add(statusLabel4);
		      mainFrame.add(statusLabel5);
		      mainFrame.add(statusLabel6);
		      mainFrame.add(statusLabel7);
		      
		      mainFrame.setVisible(true);  
		   }

		   void showTextFieldDemo(){
		      headerLabel.setText("Welcome to Fedex Tracking Application!"); 
		   
		      Label  namelabel= new Label("Enter your Tracking number: ", Label.RIGHT);
		      
		      final TextField userText = new TextField(6);
		 //     passwordText.setEchoChar('*');

		      Button loginButton = new Button("Find your package");
		   
		      loginButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {     
		            //String data = "Tracking details:";
		            Trackingnumber = Long.parseLong(userText.getText());
		        Vector<String> data = FedExTracker.getTrackingDetails(Trackingnumber);
		          //  System.out.println(data);
		            statusLabel.setText(data.elementAt(0));  
		            statusLabel2.setText(data.elementAt(1));
		            statusLabel3.setText(data.elementAt(2));  
		            statusLabel4.setText(data.elementAt(3));
		            statusLabel5.setText(data.elementAt(4));  
		            statusLabel6.setText(data.elementAt(5));
		            statusLabel7.setText(data.elementAt(6));
			           
		         }
		      }); 

		      controlPanel.add(namelabel);
		      controlPanel.add(userText);
		      controlPanel.add(loginButton);
		      mainFrame.setVisible(true);  
		   }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			GUI  awtControlDemo = new GUI();
			awtControlDemo.showTextFieldDemo();
		}

		public void start() {
			// TODO Auto-generated method stub
			
		}
		}
