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
 *     Col 0         Col1 
 *  -------------------------
 * |         Balance         |
 *  -------------------------
 * | Costs      |            |
 *  -------------------------
 * | sales      |            |
 *  -------------------------
 * | stock      |            |
 *  -------------------------
 * | in transit |            |
 *  -------------------------
 * | Profits    |            |
 *  -------------------------
 * |          [Back]         |
 *  -------------------------
 */
public class Balance extends JFrame implements ActionListener, Runnable
{
	public Balance()
	{
		this.setTitle("Balance");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Gridbag layout...
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

				//Costs label
		JLabel costsLabel = new JLabel("Costs");
		this.add(costsLabel);
		// Add the costs label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(costsLabel, constraints);
		//fillCosts label
		JLabel fillcostsLabel = new JLabel("0");
		this.add(fillcostsLabel);
		// Add the fillcosts label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(fillcostsLabel, constraints);
		//Sales label
		JLabel salesLabel = new JLabel("Sales");
		this.add(salesLabel);
		// Add the sales label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(salesLabel, constraints);
		//fillSales label
		JLabel fillsalesLabel = new JLabel("0");
		this.add(fillsalesLabel);
		// Add the fillsales label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(fillsalesLabel, constraints);
		//Stock label
		JLabel stockLabel = new JLabel("Stock");
		this.add(stockLabel);
		// Add the stock label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 2;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(stockLabel, constraints);
		//fillStock label
		JLabel fillstockLabel = new JLabel("0");
		this.add(fillstockLabel);
		// Add the fillstock label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 2;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(fillstockLabel, constraints);
		//In transit label
		JLabel intransitLabel = new JLabel("In transit");
		this.add(intransitLabel);
		// Add the intransit label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 3;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(intransitLabel, constraints);
		//fillintransit label
		JLabel fillintransitLabel = new JLabel("0");
		this.add(fillintransitLabel);
		// Add the fillintransit label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 3;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(fillintransitLabel, constraints);
		//Profits label
		JLabel profitsLabel = new JLabel("Profits");
		this.add(profitsLabel);
		// Add the Profits label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(profitsLabel, constraints);
		//fillProfits label
		JLabel fillprofitsLabel = new JLabel("0");
		this.add(fillprofitsLabel);
		// Add the fillProfits label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(fillprofitsLabel, constraints);


		//Back button...
		JButton back = new JButton("Back");
		save.addActionListener(this);
		this.add(back);
		// Add the save button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 5;	// Start on row 6
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(back, constraints);

	
		// Show window...
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Back":
			this.dispose();
			break;
		}
	}
}
