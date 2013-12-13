public class PotatoBag extends Event
{
	public PotatoBag()
	{
		interactedWith = false;
		name = "potato bag";
		descr = "There's a potato bag on the floor\n";
	}

	public String inspect()
	{
		if (!interactedWith) {
			Item pnoTuneTool = new Item("piano tuning tools");
			pnoTuneTool.setDescr("There are some piano tuning tools on the floor.\n");
			TheGreatMansion.currentRoom.addItem(pnoTuneTool);
			interactedWith = true;
			return "As you inspect the potato bag, a set of piano tuning tools fall out of it.\n";
		} else {
			return "The bag is empty.\n";
		}
	}
}
