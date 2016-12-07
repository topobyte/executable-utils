package de.topobyte.executableutils;

public class TestDelegateValidArg
{

	public static void main(String[] args)
	{
		String[] delgateArgs = { "task1" };
		DelegateExe.create().execute(delgateArgs);
	}

}
