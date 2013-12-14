import java.util.ArrayList;

/**
 * A character that can be talked to. By talked to I mean
 * it can return a random string from the ArraList thingstoSay.
 */
public class Character
{
	// Name of character. It's used to identify character when talking.
	private String name;

	// Description of character. Used in the long description of room.
	private String descr;

	// Contains all the strings that the character can say.
	private ArrayList<String> thingsToSay;

	public Character(String name)
	{
		this.name = name;
		thingsToSay = new ArrayList<String>();
	}

	/**
	 * Return random string from ArrayList thingsToSay.
	 */
	public String talkTo()
	{
		return thingsToSay.get((int)((Math.random() * 1000) % thingsToSay.size()));
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

	public void addThingToSay(String thingToSay)
	{
		thingsToSay.add(thingToSay);
	}
}
