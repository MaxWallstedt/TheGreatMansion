public class Command
{
	private String cmdStr;
	private String invokStr;
	private String descr;

	public Command(String cmdStr)
	{
		this.cmdStr = cmdStr;
	}

	public void setInvokStr(String invokStr)
	{
		this.invokStr = invokStr;
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
	}

	public String getCmdStr()
	{
		return cmdStr;
	}

	public String getInvokStr()
	{
		return invokStr;
	}

	public String getDescr()
	{
		return descr;
	}
}
