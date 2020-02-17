package cz.pavlikj3.delivery.core.utils;

import java.util.List;

public class ListUtil {

	public static <T>  boolean isEmpty(List<T> list)
	{
		boolean result = list == null || list.size() == 0;
		return result;
	}
	public static <T> T getFirst(List<T> list)
	{
		if (isEmpty(list))
		{
			return null;
		}
		T result = list.get(0);
		return result;
	}
}
