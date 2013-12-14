/**
 * Command class is just used when printing available commands in help.
 *
 * An example command is:
 *     cmdString = "go";
 *     invokStr = "go <direction>";
 *     descr = "Moves player to room located at <direction>"
 */
public class Command
{
	// Command word.
	private String cmdStr;

	// Symbolic invocation string.
	private String invokStr;

	// Describes what command does.
	private String descr;

	public Command(String cmdStr)
	{
		this.cmdStr = cmdStr;
	}

	public String getCmdStr()
	{
		return cmdStr;
	}

	public void setInvokStr(String invokStr)
	{
		this.invokStr = invokStr;
	}

	public String getInvokStr()
	{
		return invokStr;
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
	}

	public String getDescr()
	{
		return descr;
	}
}
