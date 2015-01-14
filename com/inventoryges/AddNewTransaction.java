package com.inventoryges;

import com.inventoryges.data.Transaction;
import com.inventoryges.data.TransactionType;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 *     Col 1         Col2
 *  -------------------------
 * | Type of transaction:    |
 *  -------------------------
 * | (X)PURCHASE | ( )SALE   |
 *  -------------------------
 */
public class AddNewTransaction extends JFrame implements ActionListener
{

	private Transaction mTransaction;

	public AddNewTransaction()
	{
		// Initialize the transaction object...
		mTransaction = new Transaction();

		// Initialize window...
		this.setTitle("Add new transaction");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Gridbag layout...
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		// Type label
		JLabel typeLabel = new JLabel("Type of transaction:");

		// Add the type label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1.0;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(typeLabel, constraints);

		// Purchase type radio button...
		JRadioButton purchaseRadioButton = new JRadioButton("Purchase");
		purchaseRadioButton.addActionListener(this);
		purchaseRadioButton.setSelected(true);

		// Add the purchase type radio button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 1;	// Start on row 1
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(purchaseRadioButton, constraints);

		// Sale type radio button...
		JRadioButton saleRadioButton = new JRadioButton("Sale");
		saleRadioButton.addActionListener(this);

		// Add the purchase type radio button...
		constraints.gridx = 1;	// Start on column 1
		constraints.gridy = 1;	// Start on row 1
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(saleRadioButton, constraints);

		// Type radio buttons group...
		ButtonGroup group = new ButtonGroup();
		group.add(purchaseRadioButton);
		group.add(saleRadioButton);

		// Show window...
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Purchase":
			mTransaction.setType(TransactionType.PURCHASE);
			break;
		case "Sale":
			mTransaction.setType(TransactionType.SALE);
			break;
		}
	}
}
