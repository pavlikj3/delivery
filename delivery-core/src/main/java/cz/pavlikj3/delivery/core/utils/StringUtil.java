package cz.pavlikj3.delivery.core.utils;

public class StringUtil 
{
	public static boolean isEmpty(String value)
	{
		boolean result = value == null || "".equals(value);
		return result;
	}
}
