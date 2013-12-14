/**
 * This is the potato bag in the basement.
 * When inspected, it drops the piano tuning tools.
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
public class PotatoBag extends Event
{
	public PotatoBag()
	{
		interactedWith = false;
		name = "potato bag";
		descr = "There's a potato bag on the floor\n";
	}

	/**
	 * First create the piano tuning tools and put them in the current room.
	 * Then return a useless describing string.
	 */
	public String inspect()
	{
		if (!interactedWith) {
			Item pnoTuneTool = new Item("piano tuning tools");
			pnoTuneTool.setDescr("There are some piano tuning tools on the floor.\n");
			TheGreatMansion.getCurrentRoom().addItem(pnoTuneTool);
			interactedWith = true;
			return "As you inspect the potato bag, a set of piano tuning tools fall out of it.\n";
		} else {
			return "The bag is empty.\n";
		}
	}
}
