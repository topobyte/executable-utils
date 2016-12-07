package de.topobyte.executableutils;

public class TestDelegateInvalidArg
{

	public static void main(String[] args)
	{
		String[] delgateArgs = { "foo" };
		DelegateExe.create().execute(delgateArgs);
	}

}
