import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI for the DessertItem class
 * @author Frederick Livingston, Mike Romeo
 * limitation:
 * Name: Must be a String
 * Price: Must contain only integers  (ex $1 -> 100)
 * Weight: Must be a double
 * Price/lbs, Price/doz, Number: Must contain only integers
 */
public abstract class CheckoutGUI extends JFrame implements ActionListener 
{
	
	private final static int INFO_SIZE = 30;
	private JTextField _info = new JTextField("Number of Items: 0",100);

	private String bnames[]={ "Ice Cream", "Candy", "Cookies", "Sundae"};
	//    private String lnames[]={"Name", "Price", "Weight", "Price/lbs", "Price/doz", "Number"};
	private String lnames[] = 
		{
				"Source", "Destination", "Weight", "tracking Id"
		};
	private String bnames2[]={"Enter"};
	private String mnames[]={"Reset", "Exit"};
	private JButton buttons[];
	private JLabel labels[];
	private JButton buttons2[];   
	private JTextField tfields[];
	private JMenuItem menuitems[];
	private JMenuBar bar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private int selecteditem=0;

	/**
	* Declares the Labels
	*/
	private void setlabels()
	{
		labels = new JLabel[lnames.length];
		for (int i =0; i < lnames.length; i++) 
		{
			labels[i] = new JLabel(lnames[i]);
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setEnabled(false);
		}
	}

	/**
	* Declares the Buttons
	*/
	private void setbuttons()
	{
		buttons = new JButton[bnames.length];
		for (int i=0; i< bnames.length; i++) 
		{
			buttons[i] = new JButton( bnames[i]);
			buttons[i].addActionListener(this);

		}
	}

	/**
	* Declares the TextField
	*/
	private void settextfield()
	{
		tfields = new JTextField[lnames.length];
		for (int i=0; i < lnames.length; i++) 
		{
			tfields[i] = new JTextField(INFO_SIZE);
			tfields[i].setEnabled(false);
		}
	}

	/**
	* Declares the Enter and Total Buttons
	*/
	private void setbutton2(){
		buttons2 = new JButton[bnames2.length];
		for (int i =0; i < bnames2.length; i++) 
		{
			buttons2[i] = new JButton(bnames2[i]);
			buttons2[i].addActionListener(this);
		}
	}

	private void setmenubar()
	{
		menuitems = new JMenuItem[mnames.length];
		for (int i=0; i <mnames.length; i++) 
		{
			menuitems[i] = new JMenuItem(mnames[i]);
			menuitems[i].addActionListener(this);
		}

	}

	public CheckoutGUI()
	{
		super("gui");
		
		setSize(600,300);
		setLocation(200,200);
		setlabels();
		setbuttons();
		settextfield();
		setbutton2();
		setmenubar();
		ContainerSetup();
		show();
	}




	private void resetinfo()
	{
		for (int i=0; i< lnames.length; i++) 
		{
			tfields[i].setText("");
		}
	}

	private void disablebuttons(int b)
	{
		for (int i=0; i< buttons.length; i++) 
		{
			if (b != i) buttons[i].setEnabled(false);
		}
	}

	private void inablebuttonsAll()
	{
		for (int i=0; i< buttons.length; i++) 
		{
			buttons[i].setEnabled(true);
		}
	}

	private void inableinfo(int b)
	{
		for (int i=0; i< lnames.length; i++) 
		{
			if (b ==i) 
			{
				labels[i].setEnabled(true);
				tfields[i].setEnabled(true);
			}
		} // end for
	}

	private void disableinfoAll()
	{
		for (int i=0; i <lnames.length; i++) 
		{
			labels[i].setEnabled(false);
			tfields[i].setEnabled(false);
		}
	}

	class ReceiptGUI 
	{

		private JTextArea text = new JTextArea();
		private JFrame receipt = new JFrame("Receipt");

		public ReceiptGUI(String info)
		{
			Container p = receipt.getContentPane();
			receipt.setSize(235,600);
			p.add(new JScrollPane(text),BorderLayout.CENTER);
			text.setText(info);
			text.setEditable(false);
			text.setFont(new Font("Monospaced",Font.PLAIN,12));
			receipt.show();
		}
	}

	private void ContainerSetup()
	{
		Container c = getContentPane();

		for (int i=0; i< mnames.length; i++) file.add(menuitems[i]);
		bar.add(file);
		setJMenuBar(bar);

		//North Layout
		_info.setEditable(false);
		_info.setBackground(Color.white);
		c.add(_info,BorderLayout.NORTH);

		//South Layout
		JPanel spanel = new JPanel();
		for (int i=0; i < bnames2.length; i++) spanel.add(buttons2[i]);
		c.add(spanel,BorderLayout.SOUTH);

		//Center Layout
		JPanel cpanel = new JPanel();
		cpanel.setBorder(BorderFactory.createLoweredBevelBorder());
		cpanel.setLayout(new GridLayout(lnames.length,2));
		for (int i=0; i < lnames.length; i++) 
		{
			cpanel.add(labels[i]);
			cpanel.add(tfields[i]);
		}
		c.add(cpanel,BorderLayout.CENTER);


	}

	/*public static void main (String args[] )
	{
		CheckoutGUI app = new CheckoutGUI(new Checkout());

		app.addWindowListener( new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) { System.exit(0); } 
		});

	}*/
} // end CheckoutGUI