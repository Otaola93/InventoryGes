package com.inventoryges.data.providers;

import com.inventoryges.data.Transaction;

import javax.swing.AbstractListModel;

import java.util.Collection;
import java.util.Vector;

public abstract class DataProvider extends AbstractListModel<Transaction>
{
	private boolean mLocked = false;
	protected Vector<Transaction> mTransactions = new Vector<Transaction>();

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

	protected void addElement(Transaction p)
	{
		mTransactions.add(p);
		fireIntervalAdded(this, 0, mTransactions.size());
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
		fireIntervalAdded(this, 0, mTransactions.size());
	}

	public void addAll(Collection<Transaction> c) throws LockException
	{
		if(!mLocked)
			throw new LockException("Database not locked!");
		addAllElements(c);
	}

	@Override
	public Transaction getElementAt(int pos)
	{
		return mTransactions.get(pos);
	}

	@Override
	public int getSize()
	{
		return mTransactions.size();
	}	
}
