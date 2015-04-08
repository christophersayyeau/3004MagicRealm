package Model;

import java.lang.reflect.Array;
import Control.Player;

//taken from ArrayUtils library, minor modifications to code
public class ArrayUtils {


	public static Object remove(Object array, int index) {		      
		int length = getLength(array);		
		if (index < 0 || index >= length) {		
			throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);	       
		}	      		
		Object result = Array.newInstance(array.getClass().getComponentType(), length -1);		
		System.arraycopy(array, 0, result, 0, index);	    
		if (index < length - 1) {
			System.arraycopy(array, index + 1, result, 0, length - index - 1);		    
		}
		return result;		
	}
	
	public static int getLength(Object array) {				      
		if (array == null) {		     
			return 0;		     
		}		       
		return Array.getLength(array);	
	}

	//for adding to an array, grows it by 1
	public static <T> T[] add(final T[] array, final T element) {     
		Class<?> type;       
		if (array != null){       
			type = array.getClass().getComponentType();	      
		} else if (element != null) {	    
			type = element.getClass();	       
		} else {	    
			throw new IllegalArgumentException("Arguments cannot both be null");	       
		}		
		@SuppressWarnings("unchecked") // type must be T
		final		
		T[] newArray = (T[]) copyArrayGrow1(array, type);	    
		for(int a=0; a<newArray.length; a++){
			if(newArray[a] == null){
				newArray[a] = element;
				break;
			}
		}
		//newArray[newArray.length - 1] = element;	

		return newArray;		
	}



	private static Object copyArrayGrow1(final Object array, final Class<?> newArrayComponentType) { 
		if (array != null) {      
			final int arrayLength = Array.getLength(array);
			final Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);	
			System.arraycopy(array, 0, newArray, 0, arrayLength);		
			return newArray;		
		}		
		return Array.newInstance(newArrayComponentType, 1);		
	}



	//to find in array
	public static int indexOf(String needle, String[] haystack)	{
		for (int i=0; i<haystack.length; i++)
		{
			System.out.println("Comparing "+haystack[i]+needle + haystack[i].compareTo(needle));
			if (haystack[i] != null && haystack[i].compareTo(needle) == 0 || needle == null && haystack[i] == null)	{ 
				System.out.println("Index is "+ i);
				return i;
			}
		}

		return -1;
	}

	public static int indexOf(Player needle, Player[] haystack) {
		for (int i=0; i<haystack.length; i++)
		{
			//System.out.println("Comparing "+haystack[i].getProfile().getType()+needle.getProfile().getType() + haystack[i].getProfile().getType().compareTo(needle.getProfile().getType()));
			if (haystack[i] != null && haystack[i].getProfile().getType().compareTo(needle.getProfile().getType()) == 0 || needle == null && haystack[i] == null)	{ 
				//System.out.println("Index is "+ i);
				return i;
			}
		}

		return -1;
	}
}
