import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room
{
	private ArrayList<Item> items;
	private ArrayList<Event> events;
	private ArrayList<Character> characters;
	private ArrayList<String> usableItems;
	private HashMap<String, Room> doors;
	private HashMap<String, Boolean> doorLocked;
	private String descr;

	public Room(String descr)
	{
		items = new ArrayList<Item>();
		events = new ArrayList<Event>();
		characters = new ArrayList<Character>();
		usableItems = new ArrayList<String>();
		doors = new HashMap<String, Room>();
		doorLocked = new HashMap<String, Boolean>();
		this.descr = descr;
	}

	public void addDoor(String direction, Room room, boolean locked)
	{
		doors.put(direction, room);
		doorLocked.put(direction, locked);
	}

	public ArrayList<Item> getItems()
	{
		return items;
	}

	public ArrayList<Event> getEvents()
	{
		return events;
	}

	public void addItem(Item item)
	{
		items.add(item);
	}

	public void addEvent(Event event)
	{
		events.add(event);
	}

	public void addCharacter(Character character)
	{
		characters.add(character);
	}

	public Item takeItem(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				items.remove(item);
				return item;
			}
		}

		return null;
	}

	public Event getEvent(String name)
	{
		for (Event event : events) {
			if (event.getName().equals(name)) {
				return event;
			}
		}

		return null;
	}

	public boolean hasDoor(String door)
	{
		return doors.containsKey(door);
	}

	/**
	 * Unlock the door specified by 'String door' parameter.
	 * Check that the door exists before with 'hasDoor(String door)' method.
	 */
	public void unlock(String door)
	{
		doorLocked.put(door, false);
	}

	/**
	 * Get the lock status of the door specified by 'String door' parameter.
	 * Check that the door exists before with 'hasDoor(String door)' method.
	 */
	public boolean isLocked(String door)
	{
		return doorLocked.get(door);
	}

	public String getLongDescr()
	{
		String longDescr = descr;

		for (Event event : events) {
			longDescr += event.getDescr();
		}

		for (Item item : items) {
			longDescr += item.getDescr();
		}

		for (Character character : characters) {
			longDescr += character.getDescr();
		}

		longDescr += "\n";
		longDescr += "Exits:";
		Set<String> exits = doors.keySet();

		for (String exit : exits) {
			longDescr += " " + exit;
		}

		longDescr += "\n";

		return longDescr;
	}

	public Room getRoom(String direction)
	{
		return doors.get(direction);
	}

	public boolean containsItem(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsEvent(String name)
	{
		for (Event event : events) {
			if (event.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsCharacter(String name)
	{
		for (Character character : characters) {
			if (character.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public Character getCharacter(String name)
	{
		for (Character character : characters) {
			if (character.getName().equals(name)) {
				return character;
			}
		}

		return null;
	}

	public Item take(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				items.remove(item);
				return item;
			}
		}

		return null;
	}

	public void setUsable(String name)
	{
		usableItems.add(name);
	}

	public boolean isUsable(String name)
	{
		for (String usableItem : usableItems) {
			if (usableItem.equals(name)) {
				return true;
			}
		}

		return false;
	}
}
