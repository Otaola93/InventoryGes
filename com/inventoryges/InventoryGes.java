package com.inventoryges;

import com.inventoryges.data.Product;

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

public class InventoryGes extends JFrame implements ActionListener
{
	private DefaultListModel<Product> mStock = new DefaultListModel<Product>();

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

		JButton button = new JButton("Add item");
		button.addActionListener(this);
		this.add(button);
	}

	public void actionPerformed(ActionEvent e)
	{
		mStock.addElement(new Product("ADSA"));
	}
}
