import java.util.ArrayList;

public class TheGreatMansion
{
	private static Prompt prompt;
	public static Room currentRoom;
	private static Room endRoom;
	private static Inventory inventory;
	private static ArrayList<Room> transporters;

	public static void main(String[] args)
	{
		inventory = new Inventory();
		prompt = new Prompt();
		transporters = new ArrayList<Room>();
		printWelcome();
		String cmdStr;

		createRooms();

		System.out.println(currentRoom.getLongDescr());

		boolean continueGame = true;

		while (continueGame) {
			cmdStr = prompt.getLine();
			continueGame = interpret(cmdStr);
		}

		printGoodbye();
	}

	private static boolean interpret(String cmdStr)
	{
		String[] cmdArray = cmdStr.split("\\s+");
		String cmd = cmdArray[0].toLowerCase();
		String cmdArgs = "";

		for (int i = 1; i < cmdArray.length; ++i) {
			cmdArgs += cmdArray[i].toLowerCase() + " ";
		}

		cmdArgs = cmdArgs.trim();

		if (cmd.equals("help")) {
			printHelp();
		} else if (cmd.equals("quit")) {
			System.out.println("Are you sure that you really want to quit?\n");
			String sure;

			while (!(sure = prompt.getLine()).equals("yes")
			       && !sure.equals("no")) {
				System.out.println("Please answer yes or no\n");
			}

			if (sure.equals("yes")) {
				return false;
			}

			System.out.println("I thought so.\n");
		} else if (cmd.equals("look")) {
			System.out.println(currentRoom.getLongDescr());
		} else if (cmd.equals("inventory")) {
			System.out.println("Inventory:");

			for (Item item : inventory.getItems()) {
				System.out.println("  " + item.getName());
			}

			System.out.println();
		} else if (cmd.equals("go")) {
			if (!currentRoom.hasDoor(cmdArgs)) {
				System.out.println("There's no door there!\n");
			} else if (currentRoom.isLocked(cmdArgs)) {
				System.out.println("The door is locked.\n");
			} else {
				if (currentRoom.getRoom(cmdArgs) == endRoom) {
					System.out.println("You made it! You're out of the darned mansion again!");
					return false;
				}

				currentRoom = currentRoom.getRoom(cmdArgs);

				for (Room room : transporters) {
					if (room == currentRoom) {
						int rnd = (int)((Math.random() * 10) % 2);
						currentRoom = transporters.get(rnd);
					}
				}

				System.out.println(currentRoom.getLongDescr());
			}
		} else if (cmd.equals("take")) {
			if (!currentRoom.containsItem(cmdArgs)) {
				System.out.println("No such item in the room!\n");
			} else {
				inventory.addItem(currentRoom.take(cmdArgs));
			}
		} else if (cmd.equals("throw")) {
			if (!inventory.contains(cmdArgs)) {
				System.out.println("No such item in your inventory!\n");
			} else {
				currentRoom.addItem(inventory.throwItem(cmdArgs));
			}
		} else if (cmd.equals("use")) {
			if (!inventory.contains(cmdArgs)) {
				System.out.println("No such item in your inventory\n");
			} else if (!currentRoom.isUsable(cmdArgs)) {
				System.out.println("That is pretty useless here\n");
			} else {
				System.out.println(use(cmdArgs));
			}
		} else if (cmd.equals("inspect")) {
			if (!currentRoom.containsEvent(cmdArgs)) {
				System.out.println("No such thing in the room!\n");
			} else {
				System.out.println(currentRoom.getEvent(cmdArgs).inspect());
			}
		} else {
			System.out.println("Unknown command\n");
		}

		return true;
	}

	private static String use(String itemName)
	{
		if (itemName.equals("piano tuning tools")) {
			Item goldKey = new Item("golden key");
			goldKey.setDescr("There's a golden key on the floor.\n");
			currentRoom.addItem(goldKey);
			currentRoom.getEvent("grand piano").setInteracted();
			return "As you tune the piano, and it slowly starts to sound decent,\n" +
			       "and a golden key emerges from the harmony.\n";
		} else if (itemName.equals("golden key")) {
			currentRoom.unlock("north-east");
			return "The north-east door is unlocked.\n";
		} else if (itemName.equals("dirty bundle")) {
			inventory.throwItem("dirty bundle");
			Item ironKey = new Item("iron key");
			ironKey.setDescr("There's an iron key on the floor.\n");
			inventory.addItem(ironKey);
			return "As you clean the dirty bundle, it reveals itself to be an iron key.\n";
		} else if (itemName.equals("iron key")) {
			currentRoom.unlock("south");
			return "The main entrance is now unlocked.\n";
		}

		return "";
	}

	private static void printWelcome()
	{
		System.out.println("Welcome to The Great Mansion!");
		System.out.println();
		printBackStory();
		System.out.println("Type 'help' if you need instructions.\n");
	}

	private static void printHelp()
	{
		printBackStory();
		System.out.println("Available commands:");

		for (Command cmd : prompt.getCmds()) {
			System.out.println("  " + cmd.getInvokStr());
			System.out.println("    " + cmd.getDescr());
			System.out.println();
		}
	}

	private static void printBackStory()
	{
		System.out.println("You lost your trail in the forest");
		System.out.println("and found a great mansion.");
		System.out.println();
	}

	private static void printGoodbye()
	{
		System.out.println("Thank you for playing The Great Mansion!");
		System.out.println("Goodbye! Have a great life!\n");
	}

	private static void createRooms()
	{
		Room outside = new Room("You are outside the Great Mansion.\n" +
		                        "To your north lies the entrance.\n");
		Room mainHall = new Room("You are in the main hall.\n" +
		                         "North lies the grand staircase,\n" +
		                         "west lies the dining hall,\n" +
		                         "east lies the sitting room and\n" +
		                         "south lies the main entrance.\n");
		Room grandStairCase = new Room("You are on the grand staircase.\n" +
		                               "To the west and east, the staircase goes up a floor and\n" +
		                               "south lies the main hall.\n");
		Room diningHall = new Room("You are in the dining hall.\n" +
		                           "North lies the kitchen and\n" +
		                           "east lies the main hall.\n");
		Room kitchen = new Room("You are in the kitchen.\n" +
		                        "North lies the pantry,\n" +
		                        "south lies the dining hall and\n" +
		                        "down the stairs lies the basement.\n");
		Room pantry = new Room("You are in the pantry.\n" +
		                       "South lies the kitchen.\n");
		Item potato = new Item("potato");
		potato.setDescr("On the floor lies a potato.\n");
		pantry.addItem(potato);
		Room sittingRoom = new Room("You are in the sitting room.\n" +
		                            "North lies the gaming room and\n" +
		                            "west lies the main hall.\n");
		Room gamingRoom = new Room("You are in the gaming room.\n" +
		                           "North lies the trophy room and\n" +
		                           "south lies the sitting room.\n");
		gamingRoom.addEvent(new BilliardTable());
		Room trophyRoom = new Room("You are in the trophy room.\n" +
		                           "West lies the music room and\n" +
		                           "south lies the gaming room.\n");
		trophyRoom.addEvent(new TrophyCase());
		Room musicRoom = new Room("You are in the music room.\n" +
		                          "East lies the trophy room .\n");
		musicRoom.addEvent(new GrandPiano());
		Room basement = new Room("You are in the basement.\n" +
		                         "Up the stair lies the kitchen.\n");
		basement.addEvent(new PotatoBag());
		Room bedroom1 = new Room("You are in a bedroom.\n" +
		                         "East lies a corridor and\n" +
		                         "down a trapdoor lies the pantry.\n");
		Room bedroom2 = new Room("You are in a bedroom.\n" +
		                         "East lies a corridor.\n");
		Room corridor1 = new Room("You are in a corridor.\n" +
		                          "North-west lies a bedroom,\n" +
		                          "south-west lies another bedroom,\n" +
		                          "north-east lies a passage,\n" +
		                          "south-east lies another passage and\n" +
		                          "east lies the staircase down.\n");
		Room passage1 = new Room("You are in a passage.\n" +
		                         "West lies a corridor and\n" +
		                         "east lies another corridor.\n");
		Room passage2 = new Room("You are in a passage.\n" +
		                         "North lies the washing room,\n" +
		                         "west lies a corridor and\n" +
		                         "east lies another corridor.\n");
		Room washingRoom = new Room("You are in the washing room.\n" +
		                            "South lies a passage.\n");
		washingRoom.addEvent(new Faucet());
		Room corridor2 = new Room("You are in a corridor.\n" +
		                          "North-west lies a passage,\n" +
		                          "south-west lies another passage,\n" +
		                          "north-east lies a bedroom,\n" +
		                          "south-east lies another bedroom and\n" +
		                          "west lies the staircase down.\n");
		Room bedroom3 = new Room("You are in a bedroom.\n" +
		                         "West lies a corridor.\n");
		Room bedroom4 = new Room("You are in a bedroom.\n" +
		                         "West lies a corridor.\n");
		bedroom4.addEvent(new SleepingFigure());

		outside.addDoor("north", mainHall, 0);
		mainHall.addDoor("north", grandStairCase, 0);
		mainHall.addDoor("west", diningHall, 0);
		mainHall.addDoor("east", sittingRoom, 0);
		mainHall.addDoor("south", outside, 2);
		mainHall.setUsable("iron key");
		grandStairCase.addDoor("west", corridor1, 0);
		grandStairCase.addDoor("east", corridor2, 0);
		grandStairCase.addDoor("south", mainHall, 0);
		diningHall.addDoor("north", kitchen, 0);
		diningHall.addDoor("east", mainHall, 0);
		kitchen.addDoor("north", pantry, 0);
		kitchen.addDoor("south", diningHall, 0);
		kitchen.addDoor("down", basement, 0);
		pantry.addDoor("south", kitchen, 0);
		sittingRoom.addDoor("north", gamingRoom, 0);
		sittingRoom.addDoor("west", mainHall, 0);
		gamingRoom.addDoor("north", trophyRoom, 0);
		gamingRoom.addDoor("south", sittingRoom, 0);
		trophyRoom.addDoor("west", musicRoom, 0);
		trophyRoom.addDoor("south", gamingRoom, 0);
		musicRoom.addDoor("east", trophyRoom, 0);
		musicRoom.setUsable("piano tuning tools");
		basement.addDoor("up", kitchen, 0);
		bedroom1.addDoor("east", corridor1, 0);
		bedroom1.addDoor("down", pantry, 0);
		bedroom2.addDoor("east", corridor1, 0);
		corridor1.addDoor("north-west", bedroom1, 0);
		corridor1.addDoor("south-west", bedroom2, 0);
		corridor1.addDoor("north-east", passage1, 0);
		corridor1.addDoor("south-east", passage2, 0);
		corridor1.addDoor("east", grandStairCase, 0);
		passage1.addDoor("west", corridor1, 0);
		passage1.addDoor("east", corridor2, 0);
		passage2.addDoor("north", washingRoom, 0);
		passage2.addDoor("west", corridor1, 0);
		passage2.addDoor("east", corridor2, 0);
		washingRoom.addDoor("south", passage2, 0);
		washingRoom.setUsable("dirty bundle");
		corridor2.addDoor("north-west", passage1, 0);
		corridor2.addDoor("south-west", passage2, 0);
		corridor2.addDoor("north-east", bedroom4, 1);
		corridor2.addDoor("south-east", bedroom3, 0);
		corridor2.addDoor("west", grandStairCase, 0);
		corridor2.setUsable("golden key");
		bedroom3.addDoor("west", corridor2, 0);
		bedroom4.addDoor("west", corridor2, 0);

		transporters.add(bedroom1);
		transporters.add(bedroom2);
		transporters.add(bedroom3);

		currentRoom = outside;
		endRoom = outside;
	}
}
