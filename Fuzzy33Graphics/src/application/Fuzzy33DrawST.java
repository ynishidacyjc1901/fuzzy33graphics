/*
 *  Class Fuzzy33DrawST for fuzzy33grahics
 *  
 *  Last updated 	DEC 30 2021
 *  Author 			YUKI NISHIDA
 * 
 */

package application;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/* class for drawing space-time diagrams of 3-state 3-neighbor fuzzy CA */
public class Fuzzy33DrawST extends JPanel {
	
	Fuzzy33Array[] stdiagram;	/* arrays of all steps*/
	int len, time, unit;		/* length of arrays, time steps for evolution, length of the side of a square */
	
	/* constructor */
	Fuzzy33DrawST(Fuzzy33Array[] stdiagram, int len, int time, int unit) {
		
		this.stdiagram = stdiagram;
		this.len = len;
		this.time = time;
		this.unit = unit;
		
	}	
	
	/* draw the space-time diagram */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);	/* initialization */
		  
	    for(int t = 0; t < time; t++) {
			for(int i = 0; i < len; i++) {
				g.setColor(new Color(stdiagram[t].arr[i][0].floatValue(), stdiagram[t].arr[i][1].floatValue(), stdiagram[t].arr[i][2].floatValue()));		/* RGB color */
				g.fillRect(unit * i, unit * t, unit, unit);																									/* square */
			}
		}
	    g.dispose();
	}

}
