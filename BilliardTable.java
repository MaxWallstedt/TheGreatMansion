/**
 * This is the billiard table in the gaming room.
 * It's not used for anything.
 *
 * It inherits the following from Event class:
 *     protected String name;
 *     protected String descr;
 *     protected boolean interactedWith
 *
 *     public String getDescr();
 *     public String getName();
 *     public String inspect();
 *     public void setInteractedWith();
 */
public class BilliardTable extends Event
{
	public BilliardTable()
	{
		name = "billiard table";
		descr = "There's a billiard table in the center of the room\n";
	}

	/**
	 * Nothing interesting can happen when inspecting the billiard table.
	 */
	public String inspect()
	{
		return "That's definitely a billiard table.\n";
	}
}
