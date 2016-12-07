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
