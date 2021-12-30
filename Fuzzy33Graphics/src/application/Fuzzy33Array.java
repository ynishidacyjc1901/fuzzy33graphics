/*
 *  Class Fuzzy33Array for fuzzy33grahics
 *  
 *  Last updated 	DEC 30 2021
 *  Author 			YUKI NISHIDA
 * 
 */

package application;

import java.util.Arrays;
import java.math.MathContext;
import java.math.RoundingMode;
import java.math.BigDecimal;


/* class for managing arrays of 3-state 3-neighbor fuzzy CA */
public class Fuzzy33Array {
	
	BigDecimal[][] arr;		/* vectors in the array */
	int len;				/* length of the array */
	int prec = 100;		/* accuracy of computation */
	
	/* constructor */
	Fuzzy33Array() {
		
	}
	
	/* evolution */
	Fuzzy33Array evolve(Fuzzy33Rule ruletable) {
		
		Fuzzy33Array fa = new Fuzzy33Array();								/* new array */
		fa.arr = new BigDecimal[this.len][3];	
		fa.len = this.len;
		int k;																/* index for entry of a vector */
		int pos;															/* referring position in the rule table */
		MathContext mc = new MathContext(prec, RoundingMode.HALF_UP);		/* define the accuracy */
		
		for(int i = 0; i < this.len; i++) {
			for(k = 0; k < 3; k++) {
				fa.arr[i][k] = BigDecimal.ZERO;							/* initialization */
			}
			
			/* compute from 000 to 222 */
			for(int j1 = 0; j1 < 3; j1++) {
				for(int j2 = 0; j2 < 3; j2++) {
					for(int j3 = 0; j3 < 3; j3++) {
						pos = 9 * j1 + 3 * j2 + j3;
						k = 2 - Character.getNumericValue(ruletable.index.charAt(pos));		/* 2 -> first entry (0), 1 -> second entry (1), 0 -> third entry (2) */
						fa.arr[i][k] = ((fa.arr[i][k]).add( (arr[(i - 1 + this.len) % this.len][j1]).multiply(arr[i][j2]).multiply(arr[(i + 1) % this.len][j3]) )).round(mc);	/* add x(j1)*y(j2)*z(j3) */
					}
				}
			}
		}
		return fa;
	}

	/* show the array */
	void disp() {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}
