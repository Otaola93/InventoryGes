import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.util.ArrayList;

public class InventoryGes extends JFrame implements WindowListener, ActionListener
{
	private DefaultListModel<Producto> mStock;

	private JList mList;

	public static void main(String args[])
	{
		// TODO: Show a SplashScreen and load database with the splash...
		new InventoryGes();
	}

	public InventoryGes()
	{
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addWindowListener(this);
		this.setTitle("StockGes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(625, 420);
		this.setSize(500, 240);
		this.setVisible(true);

		mStock = DBHelper.load();
		mList = new JList(mStock);
		mList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mList.setLayoutOrientation(JList.VERTICAL);
		mList.setVisibleRowCount(-1);
		this.add(mList);

		JButton button = new JButton("Add item");
		button.addActionListener(this);
		this.add(button);
	}

	public void windowOpened(WindowEvent e)
	{
	}

	public void windowClosing(WindowEvent e) 
	{
		DBHelper.save(mStock);	
	}

	public void windowClosed(WindowEvent e)
	{
	}

	public void windowIconified(WindowEvent e)
	{
	}

	public void windowDeiconified(WindowEvent e)
	{
	}

	public void windowActivated(WindowEvent e)
	{
	}

	public void windowDeactivated(WindowEvent e)
	{
	}

	public void actionPerformed(ActionEvent e)
	{
		mStock.addElement(new Producto("ADSA"));
	}
}
