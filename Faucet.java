/**
 * This is the faucet in the washing room.
 * It's not really used for anything, but it
 * symbolically washes the dirty bundle.
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
public class Faucet extends Event
{
	public Faucet()
	{
		name = "faucet";
		descr = "There's a faucet in the room.\n";
	}

	/**
	 * Imply that something dirty needs to be washed in the faucet.
	 */
	public String inspect()
	{
		return "This could probably be used to clean dirty things...\n";
	}
}
