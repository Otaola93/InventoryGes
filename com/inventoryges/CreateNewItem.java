package com.inventoryges;

import com.inventoryges.data.Product;
import com.inventoryges.data.providers.SerializedDataProvider;
import com.inventoryges.data.providers.LockException;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CreateNewItem extends JFrame implements ActionListener
{
	public CreateNewItem()
	{
		this.setTitle("CreateNewItem");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Gridbag layout...
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		//Name label
		JLabel nameLabel = new JLabel("Name");
		this.add(nameLabel);
		// Add the name label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 0;	// Start on row 0
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(nameLabel, constraints);

		// Name textfield...
		JTextField nameTextFiel = new JTextField();
		nameTextFiel.setEditable(true);
		// Add the name textfield...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 1;	// Start on row 1
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(nameTextFiel, constraints);

		//Notes label
		JLabel notesLabel = new JLabel("Notes");
		this.add(notesLabel);
		// Add the notes label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 2;	// Start on row 2
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(notesLabel, constraints);

		// Notes textfiel...
		JTextField notesTextFiel = new JTextField();
		notesTextFiel.setEditable(true);
		// Add the notes textfield...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 3;	// Start on row 3
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(notesTextFiel, constraints);

		//Where to buy label
		JLabel whereToBuyLabel = new JLabel("Where to buy");
		this.add(whereToBuyLabel);
		// Add the where to buy label...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 4;	// Start on row 4
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(whereToBuyLabel, constraints);

		// Where to buy textfield...
		JTextField whereToBuyTextFiel = new JTextField();
		whereToBuyTextFiel.setEditable(true);
		// Add the where to buy textfield...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 5;	// Start on row 5
		constraints.gridwidth = 2;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(whereToBuyTextFiel, constraints);

		// Save button...
		JButton save = new JButton("Save");
		save.addActionListener(this);
		this.add(save);
		// Add the save button...
		constraints.gridx = 0;	// Start on column 0
		constraints.gridy = 6;	// Start on row 6
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(save, constraints);

		// Cancel button...
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		this.add(cancel);
		// Add the cancel button...
		constraints.gridx = 1;	// Start on column 1
		constraints.gridy = 6;	// Start on row 7
		constraints.gridwidth = 1;	// 1 column width
		constraints.gridheight = 1;	// 1 row height
		constraints.weightx = 1;	// All the width
		constraints.weighty = 0.0;	// No verticall fill
		constraints.fill = GridBagConstraints.HORIZONTAL;	// Fill horizontally
		this.getContentPane().add(cancel, constraints);

		// Show window...
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Save":
			break;
		case "Cancel":
			break;
		}
	}
}
