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

public class DelegateExe
{

	public static DelegateTask create()
	{
		SystemOutExecutable exe = new SystemOutExecutable();
		DelegateTask task = new DelegateTask(exe, "test");

		task.addWithName("task1", TestExe1.class);
		task.addWithName("task2", TestExe2.class);

		return task;
	}

}
