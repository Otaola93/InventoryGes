package com.inventoryges;

import com.inventoryges.data.Product;
import com.inventoryges.data.providers.SerializedDataProvider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;

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

	private JList mList;

	public static void main(String args[])
	{
		new InventoryGes();
	}

	public InventoryGes()
	{
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("StockGes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(625, 420);
		this.setSize(500, 240);
		this.setVisible(true);

		mList = new JList(mStock);
		mList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mList.setLayoutOrientation(JList.VERTICAL);
		mList.setVisibleRowCount(-1);
		this.add(mList);

		JButton button = new JButton("Lock");
		button.addActionListener(this);
		this.add(button);

		JButton button2 = new JButton("Unlock");
		button2.addActionListener(this);
		this.add(button2);

		JButton button3 = new JButton("Add");
		button3.addActionListener(this);
		this.add(button3);

		JButton button4 = new JButton("Update");
		button4.addActionListener(this);
		this.add(button4);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Lock":
			mStock.getLock();
			break;
		case "Unlock":
			mStock.releaseLock();
			break;
		case "Add":
			mStock.add(new Product("ADSA"));
			break;
		case "Update":
			mStock.update();
			break;
		}
	}
}
