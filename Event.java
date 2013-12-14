/**
 * An event is a stationary item that can be inspected.
 */
public class Event
{
	// Name of event. Used to identify event when inspecting.
	protected String name;

	// Description of event. Used in the long description of the room.
	protected String descr;

	// If event changes after it has been inspected, this changes to true.
	protected boolean interactedWith;

	/**
	 * Always overridden by method in subclasses.
	 */
	public String inspect()
	{
		return "";
	}

	/**
	 * Imply that event has been interacted with.
	 */
	public void setInteracted()
	{
		interactedWith = true;
	}

	public String getName()
	{
		return name;
	}

	public String getDescr()
	{
		return descr;
	}
}
