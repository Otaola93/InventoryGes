package com.inventoryges.data.providers;

import java.lang.Exception;

public class LockException extends Exception
{
	public LockException(String message)
	{
		super(message);
	}
}
