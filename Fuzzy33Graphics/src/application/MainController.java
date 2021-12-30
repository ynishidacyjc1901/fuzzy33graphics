/*
 *  Controller of fuzzy33graphics
 *  
 *  Last updated 	DEC 30 2021
 *  Author 			YUKI NISHIDA
 * 
 */

package application;

import java.lang.Long;
import java.util.Arrays;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
	
	int unit = 10;					/* the length of the side of a square */
	int maxlen = 100;				/* the maximum length of an array */
	int maxtime = 200;				/* the maximum time steps of evolution */
	JFrame jf = new JFrame();		/* initializing the graphics frame */
	Random rand = new Random();		/* random variables for rule numbers */
	
	
	
	/* combo box */
	@FXML
	private ComboBox<String> combRulenumber, combInitial;
	
	/* text fields */
	@FXML
	private TextField txtRulenumber, txtLength, txtTime;
	
	/* buttons */
    @FXML
    private Button btnEnter, btnOK, btnCancel;
    
    /* error message area */
    @FXML
    private Label lblError;
    
    
    /* drop down the rule number box */
    public void onRuleDropdown(ActionEvent e) {
    	combRulenumber = (ComboBox)e.getSource();
    }
    
    /* drop down the initial array box */
    public void onInitialDropdown(ActionEvent e) {
    	combInitial = (ComboBox)e.getSource();
    }
    
    /* initialization */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	txtLength.setText(String.valueOf(maxlen));
    	txtTime.setText(String.valueOf(maxtime));
    	txtRulenumber.setText("0");
    	combInitial.setValue("Random -- Real");
    }  

 
    
    
    
 	/* Enter button clicked */
    @FXML
    public void onBtnEnterClicked(ActionEvent e) { 
    		
    	String whichRule = combRulenumber.getValue();
    	/* fill in the selected rule number (c.f. Main.fxml -> combRulenumber) */
    	switch(whichRule) {
    		case "3545851951512 (Slow-to-Start rule)":
    			txtRulenumber.setText("3545851951512");
    			txtRulenumber.setEditable(false);
    			break;
    		case "7469071910973 (complete conservation 1)":
    			txtRulenumber.setText("7469071910973");
    			txtRulenumber.setEditable(false);
    			break;
    		case "6213370633533 (complete conservation 2)":
    			txtRulenumber.setText("6213370633533");
    			txtRulenumber.setEditable(false);
    			break;
    		case "Random":
    			txtRulenumber.setText(String.valueOf(rand.nextLong(7625597484987L)));
    			txtRulenumber.setEditable(false);
    			break;
    		case "Others":
    			txtRulenumber.setEditable(true);
    			break;
    	}
    }
    
	
    /* main program -- OK button clicked */
    @FXML
    public void onBtnOKClicked(ActionEvent e) { 	
    	   	
    	/* initialization */
    	long rulenum = 0L;
    	int len = 0, time = 1;
    	boolean input = true;
    	
    	/* clear the graph frame */
    	jf.getContentPane().removeAll();
    	jf.dispose();

    	
    	/* input the rule number */
    	try {
    		rulenum = Long.parseLong(txtRulenumber.getText());
    		if(Long.valueOf(rulenum).compareTo(Long.valueOf(0L)) == -1 || Long.valueOf(rulenum).compareTo(Long.valueOf(7625597484987L)) > -1) {
    			lblError.setText("Enter a nonnegative integer rule number less than 3^27 = 7625597484987.");
        		input = false;
    		}
    	}
    	catch(NumberFormatException ex) {
    		lblError.setText("Enter a nonnegative integer rule number less than 3^27 = 7625597484987.");
    		input = false;
    	}

    	
    	/* input the length of the array */
    	try {
    		len = Integer.parseInt(txtLength.getText());
    		if(len <= 0 || len > maxlen) {
    			lblError.setText("Enter a positive integer length not more than " + maxlen + ".");
        		input = false;
    		}
    	}
    	catch(NumberFormatException ex) {
    		lblError.setText("Enter a positive integer length not more than " + maxlen + ".");
    		input = false;
    	}
 
    	
    	/* input the time steps of evolution */
    	try {
    		time = Integer.parseInt(txtTime.getText());
    		if(time <= 0 || time > maxtime) {
    			lblError.setText("Enter a positive integer time steps not more than " + maxtime + ".");
        		input = false;
    		}
    	}
    	catch(NumberFormatException ex) {
    		lblError.setText("Enter a positive integer time steps not more than " + maxtime + ".");
    		input = false;
    	}
    	
    	
    	/* initialization of space-time diagram */
    	Fuzzy33Array stdiagram[] = new Fuzzy33Array[time];
    	
    	/* compute space-time diagram */
    	if(input == true) {    		
    		Fuzzy33Rule ruletable = new Fuzzy33Rule(rulenum);		/* get the rule table */
    		Fuzzy33Array farr1 = new Fuzzy33Array();
    		
    		/* type of the initial array */
    		String whichInitial = combInitial.getValue();
        	switch(whichInitial) {
        		case "Random -- Real":
        			farr1 = new Fuzzy33RandomRealArray(len);		/* get the initial array of real values*/
        			break;
        		case "Random -- Integer":
        			farr1 = new Fuzzy33RandomIntegerArray(len);		/* get the initial array of 0-1 values*/
        			break;
        	}
    		
    		stdiagram[0] = farr1;
    		
    		/* evolution */
    		for(int i = 1; i < time; i++) {
    			stdiagram[i] = stdiagram[i - 1].evolve(ruletable);
    			
    			/* detect overflow or underflow */
    			if((stdiagram[i].arr[0][0]).doubleValue() + (stdiagram[i].arr[0][1]).doubleValue() + (stdiagram[i].arr[0][2]).doubleValue() < 0.975 || 
    					(stdiagram[i].arr[0][0]).doubleValue() + (stdiagram[i].arr[0][1]).doubleValue() + (stdiagram[i].arr[0][2]).doubleValue() > 1.025) {
    				lblError.setText("Overflow or underflow. Try for smaller timesteps.");
    				input = false;
    				break;
    			}
    			
    		}
    	}

		
    	/* draw the space-time diagram */
    	if(input == true) {
    		jf.setTitle("Space-time diagram");
    		jf.setSize(unit * (len + 2), unit * (time + 4));		
    		jf.getContentPane().add(new Fuzzy33DrawST(stdiagram, len, time, unit));		
    		jf.setVisible(true);
    	}
       
    }

    /* Cancel Button Clicked */
    @FXML
    public void onBtnCancelClicked(ActionEvent e) { 
    	System.exit(0);
    }
    
    
}
