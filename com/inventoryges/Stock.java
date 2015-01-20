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
 *     Col0    Col1    Col2            Col3         Col4     Col5          Col6       Col7    Col8           Col9 
 *  -------------------------------------------------------------------------------------------------------------------
 * |                                                  Stock                                                            |
 *  -------------------------------------------------------------------------------------------------------------------
 * | Pieces   |             Consumed             |              Stock               |             In transit           |
 *  -------------------------------------------------------------------------------------------------------------------
 * | Name     | Units | Average cost | total cost| Units | Average cost | total cost| Units | Average cost | total cost|
 *  -------------------------------------------------------------------------------------------------------------------
 * |   
 *  -------------------------------------------------------------------------------------------------------------------
 * | Products |             Consumed             |              Stock               |
 *  -------------------------------------------------------------------------------------------------------------------
 * | Name     | Units | Average cost | total cost| Units | Average cost | total cost| 
 *  -------------------------------------------------------------------------------------------------------------------
 * |   
 *  -------------------------------------------------------------------------------------------------------------------
 * |                                    [Calculate stock pieces for products]                                          |
 *  -------------------------------------------------------------------------------------------------------------------
 * |                                                  [Back]                                                           |
 *  -------------------------------------------------------------------------------------------------------------------
 */
public class Stock extends JFrame implements ActionListener, Runnable
{
	public Stock()
	{
		this.setTitle("Stock");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Gridbag layout...
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		//Pieces label
		JLabel piecesLabel = new JLabel("Pieces");
		this.add(piecesLabel);
		// Add the Pieces label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(piecesLabel, constraints);
		//Consumedpieces label
		JLabel consumedpiecesLabel = new JLabel("Consumed");
		this.add(consumedpiecesLabel);
		// Add the Consumedpieces label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 3;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(consumedpiecesLabel, constraints);
		//Stockpieces label
		JLabel stockpiecesLabel = new JLabel("Stock");
		this.add(stockpiecesLabel);
		// Add the Stockpieces label...
		constraints.gridx = 4;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 3;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(stockpiecesLabel, constraints);
		//Intransitpieces label
		JLabel intransitpiecesLabel = new JLabel("In transit");
		this.add(intransitpiecesLabel);
		// Add the Intransitpieces label...
		constraints.gridx = 7;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 3;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(intransitpiecesLabel, constraints);
		//Namepieces label
		JLabel namepiecesLabel = new JLabel("Name");
		this.add(namepiecesLabel);
		// Add the Namepieces label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(namepiecesLabel, constraints);
		//unitsconsumedpieces label
		JLabel unitsconsumedpiecesLabel = new JLabel("Units");
		this.add(unitsconsumedpiecesLabel);
		// Add the unitsconsumedpieces label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(unitsconsumedpiecesLabel, constraints);
		//averagecostconsumedpieces label
		JLabel averagecostconsumedpiecesLabel = new JLabel("Average cost");
		this.add(averagecostconsumedpiecesLabel);
		// Add the averagecostconsumedpieces label...
		constraints.gridx = 2;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(averagecostconsumedpiecesLabel, constraints);
		//totalcostconsumedpieces label
		JLabel totalcostconsumedpiecesLabel = new JLabel("Total cost");
		this.add(totalcostconsumedpiecesLabel);
		// Add the totalcostconsumedpieces label...
		constraints.gridx = 3;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(totalcostconsumedpiecesLabel, constraints);
		//unitsstockpieces label
		JLabel unitsstockpiecesLabel = new JLabel("Units");
		this.add(unitsstockpiecesLabel);
		// Add the unitsstockpieces label...
		constraints.gridx = 4;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(unitsstockpiecesLabel, constraints);
		//averagecoststockpieces label
		JLabel averagecoststockpiecesLabel = new JLabel("Average cost");
		this.add(averagecoststockpiecesLabel);
		// Add the averagecoststockpieces label...
		constraints.gridx = 5;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(averagecoststockpiecesLabel, constraints);
		//totalcoststockpieces label
		JLabel totalcoststockpiecesLabel = new JLabel("Total cost");
		this.add(totalcoststockpiecesLabel);
		// Add the totalcoststockpieces label...
		constraints.gridx = 6;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(totalcoststockpiecesLabel, constraints);
		//unitsintransitpieces label
		JLabel unitsintransitpiecesLabel = new JLabel("Units");
		this.add(unitsintransitpiecesLabel);
		// Add the unitsintransitpieces label...
		constraints.gridx = 7;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(unitsintransitpiecesLabel, constraints);
		//averagecostintransitpieces label
		JLabel averagecostintransitpiecesLabel = new JLabel("Average cost");
		this.add(averagecostintransitpiecesLabel);
		// Add the averagecostintransitpieces label...
		constraints.gridx = 8;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(averagecostintransitpiecesLabel, constraints);
		//totalcostintransitpieces label
		JLabel totalcostintransitpiecesLabel = new JLabel("Total cost");
		this.add(totalcostintransitpiecesLabel);
		// Add the totalcostintransitpieces label...
		constraints.gridx = 9;	// Start on column 0
		constraints.gridy = 1;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(totalcostintransitpiecesLabel, constraints);








		//Products label
		JLabel productsLabel = new JLabel("Products");
		this.add(productsLabel);
		// Add the Products label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 3;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(productsLabel, constraints);
		//Consumedproducts label
		JLabel consumedproductsLabel = new JLabel("Consumed");
		this.add(consumedproductsLabel);
		// Add the Consumedproducts label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 3;	// Start on row 0
		constraints.gridwidth = 3;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(consumedproductsLabel, constraints);
		//stockproducts label
		JLabel stockproductsLabel = new JLabel("Stock");
		this.add(stockproductsLabel);
		// Add the stockproducts label...
		constraints.gridx = 4;	// Start on column 0
		constraints.gridy = 3;	// Start on row 0
		constraints.gridwidth = 3;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(stockproductsLabel, constraints);
		//nameproducts label
		JLabel nameproductsLabel = new JLabel("Name");
		this.add(nameproductsLabel);
		// Add the nameproducts label...
		constraints.gridx = 7;	// Start on column 0
		constraints.gridy = 3;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(nameproductsLabel, constraints);
		//unitsconsumedproducts label
		JLabel unitsconsumedproductsLabel = new JLabel("Units");
		this.add(unitsconsumedproductsLabel);
		// Add the unitsconsumedproducts label...
		constraints.gridx = 1;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(unitsconsumedproductsLabel, constraints);
		//averagecostconsumedproducts label
		JLabel averagecostconsumedproductsLabel = new JLabel("Average cost");
		this.add(averagecostconsumedproductsLabel);
		// Add the averagecostconsumedproducts label...
		constraints.gridx = 2;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(averagecostconsumedproductsLabel, constraints);
		//totalcostconsumedproducts label
		JLabel totalcostconsumedproductsLabel = new JLabel("Total cost");
		this.add(totalcostconsumedproductsLabel);
		// Add the totalcostconsumedproducts label...
		constraints.gridx = 3;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(totalcostconsumedproductsLabel, constraints);
		//unitsstockproducts label
		JLabel unitsstockproductsLabel = new JLabel("Units");
		this.add(unitsstockproductsLabel);
		// Add the unitsstockproducts label...
		constraints.gridx = 4;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(unitsstockproductsLabel, constraints);
		//averagecoststockproducts label
		JLabel averagecoststockproductsLabel = new JLabel("Average cost");
		this.add(averagecoststockproductsLabel);
		// Add the averagecoststockproducts label...
		constraints.gridx = 5;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(averagecoststockproductsLabel, constraints);
		//totalcoststockproducts label
		JLabel totalcoststockproductsLabel = new JLabel("Total cost");
		this.add(totalcoststockproductsLabel);
		// Add the totalcoststockproducts label...
		constraints.gridx = 6;	// Start on column 0
		constraints.gridy = 4;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(totalcoststockproductsLabel, constraints);


		//calculate button...
		JButton calculate = new JButton("Calculate stock pieces for products");
		save.addActionListener(this);
		this.add(calculate);
		// Add the calculate button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 6;	// Start on row 6
		constraints.gridwidth = 10;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(calculate, constraints);


		//Back button...
		JButton back = new JButton("Back");
		save.addActionListener(this);
		this.add(back);
		// Add the save button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 7;	// Start on row 6
		constraints.gridwidth = 10;	// 1 column width
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
		case "Calculate stock pieces for products":
			break;
		}
	}
}
