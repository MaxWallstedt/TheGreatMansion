/**
 * The trophy case in the trophy room. Not a very interesting event.
 */
public class TrophyCase extends Event
{
	public TrophyCase()
	{
		name = "trophy case";
		descr = "There's a trophy case in the trophy room! Who would have thought!\n";
	}

	/**
	 * Return a useless describing string.
	 */
	public String inspect()
	{
		return "That's definitely a trophy case.\n";
	}
}
