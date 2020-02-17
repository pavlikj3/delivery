package cz.pavlikj3.delivery.core.utils;

public class LongUtil 
{
	public static boolean equals(Long left, Long right, boolean acceptNulls)
	{
		if (left == null && right == null)
		{
			return acceptNulls;
		}
		if (left == null || right == null)
		{
			return false;
		}
		boolean result = left.compareTo(right) == 0;
		return result;
	}
	
	public static boolean isEmpty(Long value)
	{
		boolean result = value == null || value.longValue() == 0l;
		return result;
	}
}
