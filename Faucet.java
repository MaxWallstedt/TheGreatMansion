public class Faucet extends Event
{
	public Faucet()
	{
		name = "faucet";
		descr = "There's a faucet in the room.\n";
	}

	public String inspect()
	{
		return "This could probably be used to clean dirty things...\n";
	}
}
