public class Event
{
	protected String name;
	protected String descr;
	protected boolean interactedWith;

	public Event()
	{
	}

	public String getDescr()
	{
		return descr;
	}

	public String getName()
	{
		return name;
	}

	public String inspect()
	{
		return "";
	}

	public void setInteracted()
	{
		interactedWith = true;
	}
}
