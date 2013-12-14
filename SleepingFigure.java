/**
 * The sleeping figure in the fourth bedroom.
 * It drops the dirty bundle when inspected.
 */
public class SleepingFigure extends Event
{
	public SleepingFigure()
	{
		interactedWith = false;
		name = "sleeping figure";
		descr = "There's a sleeping figure in a bed in the room.\n";
	}

	/**
	 * If not already inspected, create dirty bundle
	 * and add it to the current room.
	 * Otherwise return a useless describing string.
	 */
	public String inspect()
	{
		if (!interactedWith) {
			Item dirtyBundle = new Item("dirty bundle");
			dirtyBundle.setDescr("There's a dirty bundle on the floor.\n");
			TheGreatMansion.getCurrentRoom().addItem(dirtyBundle);
			interactedWith = true;
			return "As you inspect the sleeping figure, it drops a dirty bundle.\n";
		} else {
			return "It's sleeping, better not disturb it.\n";
		}
	}
}
