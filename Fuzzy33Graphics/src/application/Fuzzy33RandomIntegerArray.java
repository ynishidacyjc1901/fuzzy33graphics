/*
 *  Class Fuzzy33RandomIntegerArray for fuzzy33grahics
 *  
 *  superclass 		Fuzzy33Array
 *  
 *  Last updated 	DEC 30 2021
 *  Author 			YUKI NISHIDA
 * 
 */

package application;

import java.math.BigDecimal;
import java.util.Random;

/* class for making random initial arrays of 3-state 3-neighbor CA */
public class Fuzzy33RandomIntegerArray extends Fuzzy33Array {
	
	Random rand = new Random();
	/* vectors (1,0,0), (0,1,0), (0,0,1) */
	BigDecimal vecs[][] = {{BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO},{BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO},{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE}};
	

	/* constructor */
	Fuzzy33RandomIntegerArray(int len) {	
		
		super.arr = new BigDecimal[len][];
		super.len = len;
		for(int i= 0; i < len; i++) {
			super.arr[i] = new BigDecimal[3];
			
			/* choose a vector from vertices with uniform probability*/
			int rand1 = rand.nextInt(3);
			super.arr[i] = vecs[rand1];
		}
	}

}
