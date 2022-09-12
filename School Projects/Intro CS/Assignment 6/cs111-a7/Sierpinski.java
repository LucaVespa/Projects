/*************************************************************************
 *  Compilation:  javac Sierpinski.java
 *  Execution:    java Sierpinski
 *
 *  @author:
 *
 *************************************************************************/

public class Sierpinski {

    // Height of an equilateral triangle whose sides are of the specified length. 
    public static double height(double length) {
        double x = ((Math.sqrt(3))/2)*length;
        return x;

	// WRITE YOUR CODE HERE
    }

    // Draws a filled equilateral triangle whose bottom vertex is (x, y) 
    // of the specified side length. 
    public static void filledTriangle(double x, double y, double length) {

	// WRITE YOUR CODE HERE
    double [] xcoords = {x, x - (0.5*length), x + (0.5*length)};
    double [] ycoords = {y, y + height(length), y + height(length)};
    
    StdDraw.filledPolygon(xcoords, ycoords);
    return;
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled 
    // triangle has bottom vertex (x, y) and sides of the specified length. 
    public static void sierpinski(int n, double x, double y, double length) {

	// WRITE YOUR CODE HERE
    filledTriangle(x, y, length);
    
     if (n!=1){
         sierpinski(n-1, x, y + height(length), 0.5*length);
        sierpinski(n-1, x - 0.5*length, y, 0.5*length); 
         sierpinski(n-1, x + 0.5*length, y, 0.5*length);
        }
        

    }

    // Takes an integer command-line argument n; 
    // draws the outline of an equilateral triangle (pointed upwards) of length 1; 
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and 
    // draws a Sierpinski triangle of order n that fits snugly inside the outline. 
    public static void main(String[] args) {
int n = Integer.parseInt(args[0]);
double [] xcoords = {0,0.5,1};
double [] ycoords = {0,height(1),0};

StdDraw.polygon(xcoords, ycoords);

sierpinski(n, 0.5, 0, 0.5);

	// WRITE YOUR CODE HERE 
    }
}
