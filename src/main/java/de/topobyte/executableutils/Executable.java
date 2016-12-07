package de.topobyte.executableutils;

public interface Executable
{

	void exitFail();

	void printMessage(String message);

	void printMessageAndExitFail(String message);

}
