package com.inventoryges.data.providers;

import com.inventoryges.data.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class SerializedDataProvider extends DataProvider
{
	private final static String DATABASE = "database.ser";

	@Override
	protected void pull()
	{
		try
		{
			FileInputStream fis = new FileInputStream(DATABASE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			Vector<Product> stock = (Vector<Product>)ois.readObject();
			this.addAllElements(stock);
			ois.close();
			fis.close();
		}
		catch(FileNotFoundException e)
		{
			return;
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.toString());
		}
	}

	@Override
	protected void push()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(DATABASE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mProducts);
			oos.close();
			fos.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.toString());
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
	}

	@Override
	protected void lock()
	{
	}

	@Override
	protected void unlock()
	{
	}
}
