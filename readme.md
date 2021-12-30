# Name

  fuzzy33graphics.jar

# Version

  1.0.1

# Last updated

  Dec 30 2021

# Author

  Yuki NIshida

  ynishida.cyjc1901 (-a-t-) gmail.com


## Description

  A java executable jar-file that shows space-time diagrams of 3-state 3-neighbor (fuzzy) CA.


## Requirements

* java
  (Confirmed: java-17.0.1) 
* javafx
  (Confirmed: javafx-fdk-17.0.1)


## Preparation

1. Download javafx-fdk from 
	https://gluonhq.com/products/javafx/
   and unpack under "C:\Program Files\Java".

2. Set enviromnent variable JAVAFX_HOME to
	"C:\Program Files\Java\javafx-sdk-***\lib".
   (*** is your javafx version.)


## Usage

1. Double click "fuzzy33graphics.bat".

2. When GUI launched, set the following variables.

   * rule number
	Rule numbers of 3-state 3-neighbor CA are 0 ~ 7625597484986.
	Choose a rule number in the list or choose "Others" to fill another number in the textfield.
	Option "Random" generates a rule number randomly.

   * length
	Fill in the number of cells in an array.
	Integers 1 ~ 100 are allowed.

   * time
	Fill in the time steps to evolve arrays.
	Integers 1 ~ 200 are allowed.

   * initial condition
	Choose one of the initial conditions.
	   (a) Random -- Real     : vectors from whole the triangle (fuzzy)
	   (b) Random -- Integerl : vectors from the vertices the triangle (discrete)
	

   