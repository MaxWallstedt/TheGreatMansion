import java.util.ArrayList;

public class TheGreatMansion
{
	private static Prompt prompt;
	private static Room currentRoom;
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

	public static Room getCurrentRoom()
	{
		return currentRoom;
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
					System.out.println("You made it! You're out of the darned mansion again!\n");
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
				System.out.println("That is pretty useless here.\n");
			} else {
				System.out.println(use(cmdArgs));
			}
		} else if (cmd.equals("inspect")) {
			if (!currentRoom.containsEvent(cmdArgs)) {
				System.out.println("No such thing in the room!\n");
			} else {
				System.out.println(currentRoom.getEvent(cmdArgs).inspect());
			}
		} else if (cmd.equals("talk")) {
			if (!currentRoom.containsCharacter(cmdArgs)) {
				System.out.println("There's no such character in this room.\n");
			} else {
				System.out.println(currentRoom.getCharacter(cmdArgs).talkTo());
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
		Room outside = new Room("You are outside the Great Mansion.\n\n" +
		                        "To your north lies the entrance.\n");
		Room mainHall = new Room("You are in the main hall.\n\n" +
		                         "North lies the grand staircase,\n" +
		                         "west lies the dining hall,\n" +
		                         "east lies the sitting room and\n" +
		                         "south lies the main entrance.\n");
		Room grandStairCase = new Room("You are on the grand staircase.\n\n" +
		                               "To the west and east, the staircase goes up a floor and\n" +
		                               "south lies the main hall.\n");
		Room diningHall = new Room("You are in the dining hall.\n\n" +
		                           "North lies the kitchen and\n" +
		                           "east lies the main hall.\n");
		Room kitchen = new Room("You are in the kitchen.\n\n" +
		                        "North lies the pantry,\n" +
		                        "south lies the dining hall and\n" +
		                        "down the stairs lies the basement.\n");
		Room pantry = new Room("You are in the pantry.\n\n" +
		                       "South lies the kitchen.\n");
		Item potato = new Item("potato");
		potato.setDescr("On the floor lies a potato.\n");
		pantry.addItem(potato);
		Room sittingRoom = new Room("You are in the sitting room.\n\n" +
		                            "North lies the gaming room and\n" +
		                            "west lies the main hall.\n");
		Room gamingRoom = new Room("You are in the gaming room.\n\n" +
		                           "North lies the trophy room and\n" +
		                           "south lies the sitting room.\n");
		gamingRoom.addEvent(new BilliardTable());
		Room trophyRoom = new Room("You are in the trophy room.\n\n" +
		                           "West lies the music room and\n" +
		                           "south lies the gaming room.\n");
		trophyRoom.addEvent(new TrophyCase());
		Room musicRoom = new Room("You are in the music room.\n\n" +
		                          "East lies the trophy room.\n");
		musicRoom.addEvent(new GrandPiano());
		Room basement = new Room("You are in the basement.\n\n" +
		                         "Up the stair lies the kitchen.\n");
		basement.addEvent(new PotatoBag());
		Room bedroom1 = new Room("You are in a bedroom.\n\n" +
		                         "East lies a corridor and\n" +
		                         "down a trapdoor lies the pantry.\n");
		Room bedroom2 = new Room("You are in a bedroom.\n\n" +
		                         "East lies a corridor.\n");
		Character cat = new Character("cat");
		cat.setDescr("There's a cat sitting on the floor.\n");
		cat.addThingToSay("This is my room!\n");
		cat.addThingToSay("I'm a cat.\n");
		bedroom2.addCharacter(cat);
		Room corridor1 = new Room("You are in a corridor.\n\n" +
		                          "North-west lies a bedroom,\n" +
		                          "south-west lies another bedroom,\n" +
		                          "north-east lies a passage,\n" +
		                          "south-east lies another passage and\n" +
		                          "east lies the staircase down.\n");
		Room passage1 = new Room("You are in a passage.\n\n" +
		                         "West lies a corridor and\n" +
		                         "east lies another corridor.\n");
		Room passage2 = new Room("You are in a passage.\n\n" +
		                         "North lies the washing room,\n" +
		                         "west lies a corridor and\n" +
		                         "east lies another corridor.\n");
		Room washingRoom = new Room("You are in the washing room.\n\n" +
		                            "South lies a passage.\n");
		washingRoom.addEvent(new Faucet());
		Room corridor2 = new Room("You are in a corridor.\n\n" +
		                          "North-west lies a passage,\n" +
		                          "south-west lies another passage,\n" +
		                          "north-east lies a bedroom,\n" +
		                          "south-east lies another bedroom and\n" +
		                          "west lies the staircase down.\n");
		Room bedroom3 = new Room("You are in a bedroom.\n\n" +
		                         "West lies a corridor.\n");
		Room bedroom4 = new Room("You are in a bedroom.\n\n" +
		                         "West lies a corridor.\n");
		bedroom4.addEvent(new SleepingFigure());

		outside.addDoor("north", mainHall, false);
		mainHall.addDoor("north", grandStairCase, false);
		mainHall.addDoor("west", diningHall, false);
		mainHall.addDoor("east", sittingRoom, false);
		mainHall.addDoor("south", outside, true);
		mainHall.setUsable("iron key");
		grandStairCase.addDoor("west", corridor1, false);
		grandStairCase.addDoor("east", corridor2, false);
		grandStairCase.addDoor("south", mainHall, false);
		diningHall.addDoor("north", kitchen, false);
		diningHall.addDoor("east", mainHall, false);
		kitchen.addDoor("north", pantry, false);
		kitchen.addDoor("south", diningHall, false);
		kitchen.addDoor("down", basement, false);
		pantry.addDoor("south", kitchen, false);
		sittingRoom.addDoor("north", gamingRoom, false);
		sittingRoom.addDoor("west", mainHall, false);
		gamingRoom.addDoor("north", trophyRoom, false);
		gamingRoom.addDoor("south", sittingRoom, false);
		trophyRoom.addDoor("west", musicRoom, false);
		trophyRoom.addDoor("south", gamingRoom, false);
		musicRoom.addDoor("east", trophyRoom, false);
		musicRoom.setUsable("piano tuning tools");
		basement.addDoor("up", kitchen, false);
		bedroom1.addDoor("east", corridor1, false);
		bedroom1.addDoor("down", pantry, false);
		bedroom2.addDoor("east", corridor1, false);
		corridor1.addDoor("north-west", bedroom1, false);
		corridor1.addDoor("south-west", bedroom2, false);
		corridor1.addDoor("north-east", passage1, false);
		corridor1.addDoor("south-east", passage2, false);
		corridor1.addDoor("east", grandStairCase, false);
		passage1.addDoor("west", corridor1, false);
		passage1.addDoor("east", corridor2, false);
		passage2.addDoor("north", washingRoom, false);
		passage2.addDoor("west", corridor1, false);
		passage2.addDoor("east", corridor2, false);
		washingRoom.addDoor("south", passage2, false);
		washingRoom.setUsable("dirty bundle");
		corridor2.addDoor("north-west", passage1, false);
		corridor2.addDoor("south-west", passage2, false);
		corridor2.addDoor("north-east", bedroom4, true);
		corridor2.addDoor("south-east", bedroom3, false);
		corridor2.addDoor("west", grandStairCase, false);
		corridor2.setUsable("golden key");
		bedroom3.addDoor("west", corridor2, false);
		bedroom4.addDoor("west", corridor2, false);

		transporters.add(bedroom1);
		transporters.add(bedroom2);
		transporters.add(bedroom3);

		currentRoom = outside;
		endRoom = outside;
	}
}
