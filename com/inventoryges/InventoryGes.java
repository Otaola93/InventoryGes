package com.inventoryges;

import com.inventoryges.data.Product;
import com.inventoryges.data.providers.SerializedDataProvider;
import com.inventoryges.data.providers.LockException;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private SerializedDataProvider mStock = new SerializedDataProvider();

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
		this.setTitle("InventoryGes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Gridbag layout...
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		// Products list...
		JList<Product> list = new JList<Product>(mStock);
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
		this.add(update);

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
		this.add(add);

		// Add the sync button...
		constraints.gridx = 1;	// Start on column 1
		constraints.gridy = 1;	// Start on row 1
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half of the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(add, constraints);

		// Try to get the latest version of the database...
		mStock.update();

		// Show window...
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Add":
			try
			{
				mStock.add(new Product("ADSA"));
			}
			catch(LockException le)
			{
				System.out.println(le.toString());
			}
			break;
		case "Update":
			mStock.update();
			break;
		}
	}
}
