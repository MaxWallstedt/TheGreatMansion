/**
 * This is the magic grand piano in the music room.
 * It needs to be tuned with the piano tuning tools
 * so that it can magically create a golden key.
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
public class GrandPiano extends Event
{
	public GrandPiano()
	{
		interactedWith = false;
		name = "grand piano";
		descr = "There's a grand piano in the center of the room.\n" +
		        "It's playing a melody by itself.\n";
	}

	/**
	 * Imply the solution on how to tune the piano.
	 * If it has been tuned, return a useless describing string.
	 */
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
