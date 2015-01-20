package com.inventoryges;

import com.inventoryges.data.Transaction;
import com.inventoryges.data.providers.DataChangedListener;
import com.inventoryges.data.providers.DataProvider;

import javax.swing.table.AbstractTableModel;

public class TransactionTableModel extends AbstractTableModel implements DataChangedListener
{
	private final static String[] mColNames = {"Type of transaction", "Date"};

	private DataProvider mDataProvider;

	public TransactionTableModel(DataProvider dp)
	{
		super();
		mDataProvider = dp;
		mDataProvider.addListener(this);
	}

	@Override
	public String getColumnName(int col)
	{
		return mColNames[col].toString();
	}

	@Override
	public int getRowCount()
	{
		return mDataProvider.size();
	}

	@Override
	public int getColumnCount()
	{
		return mColNames.length;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		Transaction t = mDataProvider.get(row);
		switch(column)
		{
		case 0:
			return t.getType();
		case 1:
			return t.getDate();
		default:
			return null;
		}
	}

	public void dataAdded()
	{
		this.fireTableRowsUpdated(mDataProvider.size()-1, mDataProvider.size());
	}

	public void dataAllAdded()
	{
		this.fireTableDataChanged();
	}
}
