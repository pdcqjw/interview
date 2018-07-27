package cn.game.util;

import java.util.Set;

public class SetSort {

	public static Object[] sort(Set set) {
		Object[] array = set.toArray();
		return bubbleSort(array, array.length);
	}
	
	private static void swap(Object[] array, int i, int j)
	{
	    int temp = (int) array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
	
	private static Object[] bubbleSort(Object[] array, int n)
	{
	    for (int j = 0; j < n - 1; j++)         
	    {
	        for (int i = 0; i < n - 1 - j; i++) 
	        {
	        	int a = (int)array[i];
	        	int b = (int)array[i + 1];
	            if ( a < b)            
	            {
	                swap(array, i, i + 1);
	            }
	        }
	    }
	    return array;
	}

}
