package de.topobyte.executableutils;

public class DelegateExe
{

	public static DelegateTask create()
	{
		SystemOutExecutable exe = new SystemOutExecutable();
		DelegateTask task = new DelegateTask(exe, "test");

		task.add("task1", TestExe1.class);
		task.add("task2", TestExe2.class);

		return task;
	}

}
