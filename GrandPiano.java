public class GrandPiano extends Event
{
	public GrandPiano()
	{
		interactedWith = false;
		name = "grand piano";
		descr = "There's a grand piano in the center of the room.\n" +
		        "It's playing a melody by itself.\n";
	}

	public String inspect()
	{
		if (!interactedWith) {
			return "It's horribly out of tune. How convenient that you know how to tune a piano!\n" +
			       "If you only had the right tools...\n";
		} else {
			return "That's a fine grand piano!\n";
		}
	}
}
