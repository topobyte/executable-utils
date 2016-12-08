package de.topobyte.executableutils;

class DelegateClass
{

	private Class<?> clazz;
	private boolean passName;

	public DelegateClass(Class<?> clazz, boolean passName)
	{
		this.clazz = clazz;
		this.passName = passName;
	}

	public Class<?> getClazz()
	{
		return clazz;
	}

	public boolean isPassName()
	{
		return passName;
	}

}
