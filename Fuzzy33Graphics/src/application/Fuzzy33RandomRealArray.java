/*
 *  Class Fuzzy33RandomRealArray for fuzzy33grahics
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

/* class for making random initial arrays of 3-state 3-neighbor fuzzy CA */
public class Fuzzy33RandomRealArray extends Fuzzy33Array {

Random rand = new Random();

	/* constructor */
	Fuzzy33RandomRealArray(int len) {	
		
		super.arr = new BigDecimal[len][];
		super.len = len;
		for(int i= 0; i < len; i++) {
			super.arr[i] = new BigDecimal[3];
			
			/* choose a vector from a triangle with uniform probability*/
			double rand1 = rand.nextDouble(), rand2 = rand.nextDouble();			
			super.arr[i][0] = new BigDecimal(Math.min(rand1,rand2));
			super.arr[i][1] = new BigDecimal(Math.max(rand1, rand2) - Math.min(rand1, rand2));
			super.arr[i][2] = new BigDecimal(1 - Math.max(rand1, rand2));	
			
		}
	}
	
}
