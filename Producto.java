public class Producto implements java.io.Serializable
{
	private String mName;

	public Producto(String name)
	{
		mName = name;
	}

	public String toString()
	{
		return mName;
	}
}
