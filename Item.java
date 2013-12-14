/**
 * An item that can be picked up, used and thrown.
 */
public class Item
{
	// The name of the item. Used to identify item in commands.
	private String name;

	// The description of the item. Used in the long description of room.
	private String descr;

	public Item(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
	}

	public String getDescr()
	{
		return descr;
	}
}
