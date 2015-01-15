package com.inventoryges;

import com.inventoryges.data.Transaction;
import com.inventoryges.data.TransactionType;
import com.inventoryges.data.providers.DataProvider;
import com.inventoryges.data.providers.LockException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import net.sourceforge.jdatepicker.impl.SqlDateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

/**
 *     Col 1         Col2
 *  -------------------------
 * | Type of transaction:    |
 *  -------------------------
 * | (X)PURCHASE | ( )SALE   |
 *  -------------------------
 * | Date:                   |
 *  -------------------------
 * | [ Date chooser...     ] |
 *  -------------------------
 * | Products:               |
 *  -------------------------
 * | ??????????????????????? |
 * | ??????????????????????? |
 *  -------------------------
 * | [Cancel]     | [Add]    |
 *  -------------------------
 */
public class AddTransaction extends JFrame implements ActionListener
{
	private DataProvider mTransactions;
	private Transaction mTransaction;
	private SqlDateModel mDateModel;

	public AddTransaction(DataProvider t)
	{
		// Backup parameters...
		mTransactions = t;

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

		// Date label
		JLabel dateLabel = new JLabel("Date:");

		// Add the date label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 2;	// Start on row 2
		constraints.gridwidth = 2;	// 2 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1.0;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(dateLabel, constraints);

		// Date picker...
		mDateModel = new SqlDateModel(mTransaction.getDate());
		JDatePanelImpl datePanel = new JDatePanelImpl(mDateModel);
		datePanel.addActionListener(this);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		// Add date picker to layout...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 3;	// Start on row 3
		constraints.gridwidth = 2;	// 2 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1.0;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(datePicker, constraints);

		// Products label
		JLabel productsLabel = new JLabel("Products:");

		// Add the date label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 4;	// Start on row 4
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1.0;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(productsLabel, constraints);

		// TODO: Product picker...

		// TODO: Add product picker to layout...

		// Cancel button...
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		// Add the cancel button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 6;	// Start on row 6
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(cancelButton, constraints);

		// Add button...
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);

		// Add the add button...
		constraints.gridx = 1;	// Start on column 1
		constraints.gridy = 6;	// Start on row 6
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 0.5;	// Half the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(addButton, constraints);

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
		case "Date selected":
			mTransaction.setDate(mDateModel.getValue());
			break;
		case "Cancel":
			this.dispose();
			break;
		case "Add":
			try
			{
				mTransactions.getLock();
				mTransactions.add(mTransaction);
				mTransactions.releaseLock();
				this.dispose();
			}
			catch(LockException le)
			{
				JOptionPane.showMessageDialog(this, "Could not acquire database lock! Try again!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}
}
