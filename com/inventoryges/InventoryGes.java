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

public class InventoryGes extends JFrame implements ActionListener
{
	private SerializedDataProvider mStock = new SerializedDataProvider();

	public static void main(String args[])
	{
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
		constraints.gridheight = GridBagConstraints.RELATIVE;	// Height until next component
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
		this.getContentPane().add(add, constraints);

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
