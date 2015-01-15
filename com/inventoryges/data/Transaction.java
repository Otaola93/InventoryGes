package com.inventoryges.data;

import java.io.Serializable;

import java.sql.Date;

import java.util.Vector;

public class Transaction implements Serializable
{
	private TransactionType mType;
	private Date mDate;
	private Vector<Pair<Product,Integer>> mProducts;

	public Transaction()
	{
		mType = TransactionType.PURCHASE;
		mDate = new Date(System.currentTimeMillis());
		mProducts = new Vector<Pair<Product,Integer>>();
	}

	public void setType(TransactionType t)
	{
		mType = t;
	}

	public Date getDate()
	{
		return mDate;
	}

	@Override
	public String toString()
	{
		return mType.toString() + " on " + mDate.toString();
	}
}
