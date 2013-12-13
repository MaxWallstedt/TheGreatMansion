import java.util.ArrayList;

public class Inventory
{
	private ArrayList<Item> items;

	public Inventory()
	{
		items = new ArrayList<Item>();
	}

	public void addItem(Item item)
	{
		items.add(item);
	}

	public boolean contains(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public Item getItem(String name)
	{
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}

		return null;
	}

	public ArrayList<Item> getItems()
	{
		return items;
	}

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
