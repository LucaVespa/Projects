/*************************************************************************
 *  Compilation:  javac OrderCheck.java
 *  Execution:    java OrderCheck 5 10 15 2
 *
 *  @author:
 *
 *  Prints true if the four integer inputs are in strictly ascending
 *  or descending order, false otherwise
 *
 *  % java OrderCheck 5 10 15 2
 *  false
 *
 *  % java OrderCheck 15 11 9 4
 *  true
 *
 *************************************************************************/

public class OrderCheck {

    public static void main(String[] args) {

	// WRITE YOUR CODE HERE
	double w = Double.parseDouble(args[0]);
    double x = Double.parseDouble(args[1]);
    double y = Double.parseDouble(args[2]);
    double z = Double.parseDouble(args[3]);

    boolean a = false;
    a = ((w > x) && (x > y) && (y > z)) || ((z > y) && (y > x) && (x > w));

   System.out.print(a);
    }
}
