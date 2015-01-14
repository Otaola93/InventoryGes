package com.inventoryges.data;

import java.io.Serializable;

public class Pair<L,R> implements Serializable
{
	private L mLeft;
	private R mRight;

	public Pair(L left, R right)
	{
		mLeft = left;
		mRight = right;
	}

	public L getLeft()
	{
		return mLeft;
	}

	public R getRight()
	{
		return mRight;
	}
}
