import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * The rooms that make up the game.
 */
public class Room
{
	// A list of items currently in the room
	private ArrayList<Item> items;

	// A list of the items that can be used in the room.
	private ArrayList<String> usableItems;

	// A list of events currently in the room
	private ArrayList<Event> events;

	// A list of characters currently in the room
	private ArrayList<Character> characters;

	// HashMap linking directions to other rooms.
	private HashMap<String, Room> doors;

	// HashMap with the same keys as above
	// indicating whether the door is locked.
	private HashMap<String, Boolean> doorLocked;

	// Room description
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

	/**
	 * Add a door to the room, link it to another room
	 * and specify whether the door shall be locked.
	 */
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

	/**
	 * Add specified item to room.
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}

	/**
	 * Add specified event to room.
	 */
	public void addEvent(Event event)
	{
		events.add(event);
	}

	/**
	 * Add specified character to room.
	 */
	public void addCharacter(Character character)
	{
		characters.add(character);
	}

	/**
	 * Return the specified event objectif it exists in the room.
	 * Otherwise return null;
	 */
	public Event getEvent(String name)
	{
		for (Event event : events) {
			if (event.getName().equals(name)) {
				return event;
			}
		}

		return null;
	}

	/**
	 * Return true if the specified door exists in the room.
	 */
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

	/**
	 * Create and return a long description of everything in the room.
	 */
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

	/**
	 * Return the specified room object.
	 * Always run 'hasDoor' method before to ensure
	 * that the door actually exists in the rom.
	 */
	public Room getRoom(String direction)
	{
		return doors.get(direction);
	}

	/**
	 * Return true if specified item exists in the room.
	 * Otherwise return false.
	 */
	public boolean containsItem(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Return true if specified event exists in the room.
	 * Otherwise return false.
	 */
	public boolean containsEvent(String name)
	{
		for (Event event : events) {
			if (event.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Return true if specified character exists in the room.
	 * Otherwise return false.
	 */
	public boolean containsCharacter(String name)
	{
		for (Character character : characters) {
			if (character.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Return the specified character if it exists in the room.
	 * Otherwise return null.
	 */
	public Character getCharacter(String name)
	{
		for (Character character : characters) {
			if (character.getName().equals(name)) {
				return character;
			}
		}

		return null;
	}

	/**
	 * Remove and return the specified item object if it exists in the room.
	 * Otherwise return null;
	 */
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

	/**
	 * Add an item to the room's list of usable items.
	 */
	public void setUsable(String name)
	{
		usableItems.add(name);
	}

	/**
	 * Return true if specified door is locked.
	 * Return falsee if door is not locked or doesn't exist.
	 */
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
