package Model;

import java.lang.reflect.Array;

//taken from ArrayUtils
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
	
	
}
