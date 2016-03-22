import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**<ol>
 * This UnitTest property tests Java's Arrays.sort method. </br>
 * The properties tested are as follows: </br>
 * <li>Length is the same after sorting as it was before</li>
 * <li>The output of an array being sorted once is the same as being sorted twice</li>
 * <li>All the values of the output are values of the input</li>
 * </ol>*/
public class ArraysSortTest {
	/** Used to store the random int arrays */
	private ArrayList<int[]> arrays;
	private Random rand;
	
	/**This is called before every test. This initializes the random int arrays. This will only do something the first time it is called. */
	@Before
	public void setup() {
		//Initialize random arrays (only runs once)
		if (arrays == null) {
			rand = new Random();
			arrays = new ArrayList<int[]>();
			
			//Generate 100 arrays with length i
			for(int i = 0; i < 100; i++) {
				int[] arr = new int[i];
				for (int j = 0; j < i; j++)
					arr[j] = rand.nextInt();
				
				//Add array to list
				arrays.add(arr);
			}
		}
	}
	
	/**This test checks that the length of the array is the same after being sorted as it was before being sorted*/
	@Test
	public void testLength() {
		for (int i = 0; i < 100; i++) {
			//Get array from list of randomly created arrays
			int[] arr = Arrays.copyOf(arrays.get(i), arrays.get(i).length);
			
			//Store length before sorting
			int lengthBeforeSort = arr.length;
			
			//Sort
			Arrays.sort(arr);
			
			//Check if length is the same after sort, if not, then fail
			if (arr.length != lengthBeforeSort)
				fail();
		}
		
		//The test didn't fail, so pass it
		assertTrue(true);
	}
	
	/** This test checks if an array is sorted twice, the output should not be different from sorting it once */
	@Test
	public void testIdempotentandPure() {
		for (int i = 0; i < 100; i++) {
			//Get array from list of randomly created arrays
			int[] arr = Arrays.copyOf(arrays.get(i), arrays.get(i).length);
			
			//Sort
			Arrays.sort(arr);
			
			//Create a copy of the sorted array to test idempotentcy
			int[] arraySorted = Arrays.copyOf(arr, arr.length);
			
			//Sort again
			Arrays.sort(arr);
			
			//Check idempotentcy by comparing if every element from the first sort is in the same place after the second sort
			for (int j = 0; j < i; j++)
				//If there is a point where array sorted once does not equal the array sorted twice
				if (arr[j] != arraySorted[j])
					fail();
		}
		
		//The test didn't fail, so pass it
		assertTrue(true);
	}
	
	/** This test checks for the property that all output value should only contain values in the input */
	@Test
	public void testOutputOnlyContainsInput() {
		for (int i = 0; i < 100; i++) {
			//Get array from list of randomly created arrays
			int[] arr = Arrays.copyOf(arrays.get(i), arrays.get(i).length);
			
			//Store the (unique) values of the input to check if the output only contains these
			ArrayList<Integer> valuesInInput = new ArrayList<Integer>();
			for (int j = 0; j < i; j++)
				if (!valuesInInput.contains(arr[j]))
					valuesInInput.add(arr[j]);
			
			//Sort
			Arrays.sort(arr);
			
			//Compare the values of the output to the list of input values
			for (int j = 0; j < i; j++) {
				int value = arr[j];
				boolean found = false;
				
				//Check all values of the input
				for (int k = 0; k < valuesInInput.size(); k++)
					if (value == valuesInInput.get(k))
						found = true;
				
				//If a output value does not match an input value, then fail
				if (!found)
					fail();
			}
		}
		
		//The test didn't fail, so pass it
		assertTrue(true);
	}
}
