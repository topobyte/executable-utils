package de.topobyte.executableutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelegateTask
{

	private Executable exe;
	private String name;

	private List<String> keys = new ArrayList<>();
	private Map<String, Class<?>> classes = new HashMap<>();

	public DelegateTask(Executable exe, String name)
	{
		this.exe = exe;
		this.name = name;
	}

	public void add(String name, Class<?> c)
	{
		keys.add(name);
		classes.put(name, c);
	}

	public void execute(String[] args)
	{
		if (args.length < 1) {
			exe.printMessage(String.format("Usage: %s <task>", name));
			printTaskNames();
			exe.exitFail();
		}
		String task = args[0];
		Class<?> c = classes.get(task);
		if (c == null) {
			exe.printMessage(String.format(
					"Unknown task '%s'. Please specifiy a valid task.", task));
			printTaskNames();
			exe.exitFail();
		}
		try {
			Method method = c.getMethod("main", String[].class);
			String[] taskArgs = new String[args.length - 1];
			for (int i = 0; i < taskArgs.length; i++) {
				taskArgs[i] = args[i + 1];
			}
			method.invoke(null, (Object) taskArgs);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException e) {
			exe.printMessageAndExitFail(
					"Error while starting main method for task");
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
			exe.exitFail();
		}
	}

	private void printTaskNames()
	{
		exe.printMessage("Valid tasks:");
		for (String key : keys) {
			exe.printMessage(key);
		}
	}

}
