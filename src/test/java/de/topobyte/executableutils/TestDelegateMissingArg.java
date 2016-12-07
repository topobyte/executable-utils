package de.topobyte.executableutils;

public class TestDelegateMissingArg
{

	public static void main(String[] args)
	{
		String[] delgateArgs = {};
		DelegateExe.create().execute(delgateArgs);
	}

}
