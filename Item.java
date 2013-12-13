public class Item
{
	private String name;
	private String descr;
	private int id;

	public Item(String name)
	{
		this.name = name;
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public String getDescr()
	{
		return descr;
	}

	public int getId()
	{
		return id;
	}
}
