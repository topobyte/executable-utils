package de.topobyte.executableutils;

public class SystemOutExecutable implements Executable
{

	@Override
	public void exitFail()
	{
		System.exit(1);
	}

	@Override
	public void printMessage(String message)
	{
		System.out.println(message);
	}

	@Override
	public void printMessageAndExitFail(String message)
	{
		System.out.println(message);
		System.exit(1);
	}

}
