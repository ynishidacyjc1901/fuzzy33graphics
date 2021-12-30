/*
 *  Class Fuzzy33Rule for fuzzy33grahics
 *  
 *  Last updated 	DEC 30 2021
 *  Author 			YUKI NISHIDA
 * 
 */

package application;

import java.math.BigInteger;

/* class for rule tables of 3-state 3-neighbor fuzzy CA */
public class Fuzzy33Rule {

	long rulenum;		/* rule number in decimal number */
	String index;		/* rule table from 222 to 000 */	
	
	/* constructor */
	Fuzzy33Rule(long rulenum) {

		this.rulenum = rulenum;
		BigInteger rulenum2 = new BigInteger(String.valueOf(rulenum + 7625597484987L));		/* add 3^27 to have a 27 digits ternary number */
		String ternary = rulenum2.toString(3);												/* convert to ternary number */
		index = ternary.substring(1);														/* delete the initial digit */
		
	}
	
	/* show the rule table */
	void disp() {
			System.out.println(index);
	}

}
		

