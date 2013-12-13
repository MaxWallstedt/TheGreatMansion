import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prompt
{
	private BufferedReader buf;
	private ArrayList<Command> cmds;

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
	}

	public String getLine()
	{
		System.out.print("> ");

		try {
			return buf.readLine();
		} catch (IOException e) {
			throw new Error("Problem reading from stdin");
		}
	}

	public ArrayList<Command> getCmds()
	{
		return cmds;
	}
}
