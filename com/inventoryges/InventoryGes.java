package com.inventoryges;

import com.inventoryges.data.Transaction;
import com.inventoryges.data.providers.SerializedDataProvider;
import com.inventoryges.data.providers.LockException;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SplashScreen;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class InventoryGes extends JFrame implements ActionListener
{
	private SerializedDataProvider mTransactions = new SerializedDataProvider();

	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(UnsupportedLookAndFeelException e)
		{
			// handle exception
			System.out.println(e.toString());
		}
		catch(ClassNotFoundException e)
		{
			// handle exception
			System.out.println(e.toString());
		}
		catch(InstantiationException e)
		{
			// handle exception
			System.out.println(e.toString());
		}
		catch(IllegalAccessException e)
		{
			// handle exception
			System.out.println(e.toString());
		}

		new InventoryGes();
	}

	public InventoryGes()
	{
		// Get the splash screen handler...
		SplashScreen splash = SplashScreen.getSplashScreen();
		if(splash == null)
		{
			System.out.println("Could not manage the splash screen...");
			return;
		}
		Rectangle splashBounds = splash.getBounds();
		Rectangle textBounds = new Rectangle(0, (int)splashBounds.getHeight()-20, (int)splashBounds.getWidth(), 20);
		int textX = 10;
		int textY = (int)textBounds.getY() + 15;

		// For drawing on the splash...
		Graphics2D splashGraphics = splash.createGraphics();
		if(splashGraphics == null)
		{
			System.out.println("Cannot draw on splash screen...");
			return;
		}

		// Tell what are we doing...
		splashGraphics.setComposite(AlphaComposite.Clear);
		splashGraphics.fill(textBounds);
		splashGraphics.setPaintMode();
		splashGraphics.setColor(Color.BLACK);
		splashGraphics.fill(textBounds);
		splashGraphics.setColor(Color.WHITE);
		splashGraphics.drawString("Building the interface...", textX, textY);
		splash.update();

		// Start creating the window...
		this.setTitle("InventoryGes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Gridbag layout...
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		// Transactions list...
		JList<Transaction> list = new JList<Transaction>(mTransactions);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		// Inside a scroll...
		JScrollPane pane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(300, 200));

		// Add the list to GridBag...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1.0;	// Fill with priority
		constraints.weighty = 1.0;	// Fill with priority
		constraints.fill = GridBagConstraints.BOTH;	// Fill in both directions
		this.getContentPane().add(pane, constraints);

		// Sync button...
		JButton update = new JButton("Update");
		update.addActionListener(this);

		// Add the sync button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 1;	// Start on row 1
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half of the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(update, constraints);

		// Add button...
		JButton add = new JButton("Add");
		add.addActionListener(this);

		// Add the add button...
		constraints.gridx = 1;	// Start on column 1
		constraints.gridy = 1;	// Start on row 1
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half of the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(add, constraints);

		// Tell what are we doing...
		splashGraphics.setComposite(AlphaComposite.Clear);
		splashGraphics.fill(textBounds);
		splashGraphics.setPaintMode();
		splashGraphics.setColor(Color.BLACK);
		splashGraphics.fill(textBounds);
		splashGraphics.setColor(Color.WHITE);
		splashGraphics.drawString("Opening database...", textX, textY);
		splash.update();

		// Try to get the latest version of the database...
		mTransactions.update();

		// Hide splash...
		splash.close();

		// Show window...
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Add":
			new AddTransaction(mTransactions);
			break;
		case "Update":
			mTransactions.update();
			break;
		}
	}
}
