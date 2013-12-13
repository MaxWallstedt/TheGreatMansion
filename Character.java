import java.util.ArrayList;

public class Character
{
	private String name;
	private String descr;
	private ArrayList<String> thingsToSay;

	public Character(String name)
	{
		this.name = name;
		thingsToSay = new ArrayList<String>();
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
	}

	public String getDescr()
	{
		return descr;
	}

	public String getName()
	{
		return name;
	}

	public String talkTo()
	{
		return thingsToSay.get((int)((Math.random() * 1000) % thingsToSay.size()));
	}

	public void addThingToSay(String thingToSay)
	{
		thingsToSay.add(thingToSay);
	}
}
