import java.util.ArrayList;

/**
 * A list of items that the player has picked up.
 */
public class Inventory
{
	// The list of items that the player has picked up.
	private ArrayList<Item> items;

	public Inventory()
	{
		items = new ArrayList<Item>();
	}

	/**
	 * Add an item to the inventory.
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}

	/**
	 * Return the entire list of items.
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}

	/**
	 * Return true if inventory contains the
	 * specified item, otherwise false.
	 */
	public boolean contains(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Return the specified item object from inventory
	 * if it exists. Otherwise, return null.
	 */
	public Item getItem(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * Delete and return the specified item object
	 * if it exists. Otherwise return null.
	 */
	public Item throwItem(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				items.remove(item);
				return item;
			}
		}

		return null;
	}
}
