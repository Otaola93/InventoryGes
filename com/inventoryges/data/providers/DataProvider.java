package com.inventoryges.data.providers;

import com.inventoryges.data.Product;

import javax.swing.AbstractListModel;

import java.util.Collection;
import java.util.Vector;

public abstract class DataProvider extends AbstractListModel<Product>
{
	private boolean mLocked = false;
	protected Vector<Product> mProducts = new Vector<Product>();

	protected abstract void pull();
	protected abstract void push();
	protected abstract void lock();
	protected abstract void unlock();

	public void getLock()
	{
		lock();
		mLocked = true;
		pull();
	}

	public void releaseLock()
	{
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

	public void add(Product p)
	{
		mProducts.add(p);
		fireIntervalAdded(this, 0, mProducts.size());
	}

	public void addAll(Collection<Product> c)
	{
		mProducts.clear();
		mProducts.addAll(c);
		fireIntervalAdded(this, 0, mProducts.size());
	}

	@Override
	public Product getElementAt(int pos)
	{
		return mProducts.get(pos);
	}

	@Override
	public int getSize()
	{
		return mProducts.size();
	}	
}
