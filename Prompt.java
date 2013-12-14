import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class to get input from the terminal.
 * It also contains a list of commands that the 'help'
 * command reads from when printing available commands.
 */
public class Prompt
{
	// Buffered reader from System.in
	private BufferedReader buf;

	// The list of available commands. Not used to interpret
	// the commands, but to print available commands.
	private ArrayList<Command> cmds;

	/**
	 * Create all commmands and the buffered reader.
	 */
	public Prompt()
	{
		cmds = new ArrayList<Command>();

		buf = new BufferedReader(new InputStreamReader(System.in));
		cmds = new ArrayList<Command>();

		Command help = new Command("help");
		help.setInvokStr("help");
		help.setDescr("Print these instructions");
		cmds.add(help);

		Command quit = new Command("quit");
		quit.setInvokStr("quit");
		quit.setDescr("Exit the game");
		cmds.add(quit);

		Command look = new Command("look");
		look.setInvokStr("look");
		look.setDescr("Get detailed description of what you see");
		cmds.add(look);

		Command inventory = new Command("inventory");
		inventory.setInvokStr("inventory");
		inventory.setDescr("Print the contents of your inventory");
		cmds.add(inventory);

		Command go = new Command("go");
		go.setInvokStr("go <direction>");
		go.setDescr("Go to the room located at <direction>");
		cmds.add(go);

		Command take = new Command("take");
		take.setInvokStr("take <item>");
		take.setDescr("Take <item> from the current room and put it in your inventory");
		cmds.add(take);

		Command _throw = new Command("throw");
		_throw.setInvokStr("throw <item>");
		_throw.setDescr("Throw <item> from your inventory and put it in the current room");
		cmds.add(_throw);

		Command use = new Command("use");
		use.setInvokStr("use <item>");
		use.setDescr("Use <item> in your inventory");
		cmds.add(use);

		Command inspect = new Command("inspect");
		inspect.setInvokStr("inspect <thing>");
		inspect.setDescr("Inspect <thing> in the current room");
		cmds.add(inspect);

		Command talk = new Command("talk");
		talk.setInvokStr("talk <character>");
		talk.setDescr("Talk to <character>");
		cmds.add(talk);
	}

	/**
	 * Return the entire list of commands.
	 */
	public ArrayList<Command> getCmds()
	{
		return cmds;
	}

	/**
	 * Print a prompt and .return the input from System.in as a String.
	 * If this fails it throws an error.
	 */
	public String getLine()
	{
		System.out.print("> ");

		try {
			return buf.readLine();
		} catch (IOException e) {
			throw new Error("Problem reading from stdin");
		}
	}
}
