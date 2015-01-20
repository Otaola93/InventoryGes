package com.inventoryges.data.providers;

import com.inventoryges.data.Transaction;

import java.util.Collection;
import java.util.Vector;
import java.util.ArrayList;

public abstract class DataProvider
{
	private boolean mLocked = false;
	protected Vector<Transaction> mTransactions = new Vector<Transaction>();
	private ArrayList<DataChangedListener> mListeners = new ArrayList<DataChangedListener>();

	protected abstract void pull();
	protected abstract void push();
	protected abstract void lock();
	protected abstract void unlock();

	public void getLock()
	{
		if(mLocked)
			return;
		lock();
		mLocked = true;
		pull();
	}

	public void releaseLock()
	{
		if(!mLocked)
			return;
		push();
		unlock();		
		mLocked = false;
	}

	public void update()
	{
		if(mLocked)
			return;
		pull();
	}

	public void addListener(DataChangedListener l)
	{
		mListeners.add(l);
	}

	protected void addElement(Transaction p)
	{
		mTransactions.add(p);
		for(DataChangedListener l : mListeners)
			l.dataAdded();
	}

	public void add(Transaction p) throws LockException
	{
		if(!mLocked)
			throw new LockException("Database not locked!");
		addElement(p);
	}

	protected void addAllElements(Collection<Transaction> c)
	{
		mTransactions.clear();
		mTransactions.addAll(c);
		for(DataChangedListener l : mListeners)
			l.dataAllAdded();
	}

	public void addAll(Collection<Transaction> c) throws LockException
	{
		if(!mLocked)
			throw new LockException("Database not locked!");
		addAllElements(c);
	}

	public Transaction get(int i)
	{
		return mTransactions.get(i);
	}

	public int size()
	{
		return mTransactions.size();
	}
}
