package Model;

import java.lang.reflect.Array;

//taken from ArrayUtils library, one modification to code
public class ArrayUtils {
	
	
	public static Denizen[] remove(Denizen[] array, int index) {			     
		return (Denizen[]) remove((Object) array, index);
	}
	
	private static Object remove(Object array, int index) {		      
		int length = getLength(array);		
		if (index < 0 || index >= length) {		
			throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);	       
		}	      		
		Object result = Array.newInstance(array.getClass().getComponentType(), length );//don't make array smaller		
		System.arraycopy(array, 0, result, 0, index);	    
		if (index < length - 1) {	    
			System.arraycopy(array, index + 1, result, index, length - index - 1);		    
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
		newArray[newArray.length - 1] = element;		
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
}
