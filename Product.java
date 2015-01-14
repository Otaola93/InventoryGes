import java.io.Serializable;

public class Product implements Serializable
{
	private String mName;

	public Product(String name)
	{
		mName = name;
	}

	public String toString()
	{
		return mName;
	}
}
