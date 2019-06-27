package utils;

import java.util.Iterator;
import java.util.List;

public class StringUtils 
{
	public static <T> String toStringAsBoxedSequenceOfItems(final Iterable<T> items)
	{
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		
		Iterator<T> it = items.iterator();
		if (it.hasNext())
		{
			sb.append(it.next().toString());
		}
		
		while (it.hasNext())
		{
			sb.append(',');
			sb.append(' ');
			sb.append(it.next());
		}
		
		sb.append(']');

		return sb.toString();
	}
	
	public static <T> String toStringAsOneItemPerLine(final Iterable<T> items)
	{
		StringBuilder sb = new StringBuilder();
		
		Iterator<T> it = items.iterator();
		while (it.hasNext())
		{
			sb.append(it.next());
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
}
