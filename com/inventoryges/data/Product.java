package com.inventoryges.data;

import java.io.Serializable;

public class Product implements Serializable
{
	private String mName;
	private String mNotes;
	private String mUrl;

	public Product(String name, String notes, String url)
	{
		mName = name;
		mNotes = notes;
		mUrl = url;
	}

	public String toString()
	{
		return mName;
	}
}
