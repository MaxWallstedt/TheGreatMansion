public class BilliardTable extends Event
{
	public BilliardTable()
	{
		name = "billiard table";
		descr = "There's a billiard table in the center of the room\n";
	}

	public String inspect()
	{
		return "That's definitely a billiard table.\n";
	}
}
