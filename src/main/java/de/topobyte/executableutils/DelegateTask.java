// Copyright 2016 Sebastian Kuerten
//
// This file is part of executable-utils.
//
// executable-utils is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// executable-utils is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with executable-utils. If not, see <http://www.gnu.org/licenses/>.

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
	private Map<String, DelegateClass> classes = new HashMap<>();

	public DelegateTask(Executable exe, String name)
	{
		this.exe = exe;
		this.name = name;
	}

	/**
	 * Add a named subcommand {@code name} that will run the specified class by
	 * invoking its {@code main(String[] args)} method.
	 * 
	 * @param name
	 *            the subcommand that can be used to invoke this subtask
	 * @param c
	 *            the class that implements the subtask
	 */
	public void addClassic(String name, Class<?> c)
	{
		keys.add(name);
		classes.put(name, new DelegateClass(c, false));
	}

	/**
	 * Add a named subcommand {@code name} that will run the specified class by
	 * invoking its {@code main(String name, String[] args)} method.
	 * 
	 * @param name
	 *            the subcommand that can be used to invoke this subtask
	 * @param c
	 *            the class that implements the subtask
	 */
	public void addWithName(String name, Class<?> c)
	{
		keys.add(name);
		classes.put(name, new DelegateClass(c, true));
	}

	public void execute(String[] args)
	{
		if (args.length < 1) {
			exe.printMessage(String.format("Usage: %s <task>", name));
			printTaskNames();
			exe.exitFail();
		}
		String task = args[0];
		DelegateClass c = classes.get(task);
		if (c == null) {
			exe.printMessage(String.format(
					"Unknown task '%s'. Please specifiy a valid task.", task));
			printTaskNames();
			exe.exitFail();
		}
		try {
			Class<?> clazz = c.getClazz();
			String[] taskArgs = new String[args.length - 1];
			for (int i = 0; i < taskArgs.length; i++) {
				taskArgs[i] = args[i + 1];
			}

			if (!c.isPassName()) {
				Method method = clazz.getMethod("main", String[].class);
				method.invoke(null, (Object) taskArgs);
			} else {
				Method method = clazz.getMethod("main", String.class,
						String[].class);
				method.invoke(null, name + " " + task, taskArgs);
			}
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException e) {
			exe.printMessageAndExitFail("Error while starting main method for task");
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
