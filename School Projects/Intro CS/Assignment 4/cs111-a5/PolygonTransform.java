/*************************************************************************
 *  Compilation:  javac PolygonTransform.java
 *  Execution:    java PolygonTransform
 *
 *  @author:
 *
 *************************************************************************/

public class PolygonTransform {


    // Returns a new array that is an exact copy of the given array. 
    // The given array is not mutated. 
    public static double[] copy(double[] array) {

	double [] dupe = new double [array.length];
    for (int i = 0; i < array.length; i++){
        dupe[i] = array[i];
    }
    return dupe;
    }
    
    // Scales the given polygon by the factor alpha. 
    public static void scale(double[] x, double[] y, double alpha) {

      for (int i = 0; i < x.length; i++){
          x[i] = alpha*x[i];
      }
       for (int i = 0; i < y.length; i++){
          y[i] = alpha*y[i];
      }
    }
    
    // Translates the given polygon by (dx, dy). 
    public static void translate(double[] x, double[] y, double dx, double dy) {

        for (int i = 0; i < x.length; i++){
          x[i] = dx + x[i];
      }
       for (int i = 0; i < y.length; i++){
          y[i] = dy + y[i];
      }

    }
    
    // Rotates the given polygon theta degrees counterclockwise, about the origin. 
    public static void rotate(double[] x, double[] y, double theta) {

	  theta = Math.toRadians(theta);
        double [] xcos = new double [x.length];
        for (int i = 0; i < x.length; i++){
          xcos[i] = Math.cos(theta)*x[i];
      }
        double [] xsin = new double [x.length];
        for (int i = 0; i < x.length; i++){
          xsin[i] = Math.sin(theta)*x[i];
      }
        double [] ycos = new double [y.length];
        for (int i = 0; i < y.length; i++){
          ycos[i] = Math.cos(theta)*y[i];
      }
        double [] ysin = new double [y.length];
         for (int i = 0; i < y.length; i++){
          ysin[i] = Math.sin(theta)*y[i];
      }
      for (int i = 0; i < x.length; i++){
          x[i] =  xcos[i] - ysin[i];
      }
      for (int i = 0; i < x.length; i++){
          y[i] =  ycos[i] + xsin[i];
      }
    
    }

    // Tests each of the API methods by directly calling them. 
    public static void main(String[] args) {

StdDraw.setScale(-5.0, +5.0); 
double[] x = { 0, 1, 1, 0 }; 
double[] y = { 0, 0, 2, 1 }; 
double theta = 45.0; 
StdDraw.setPenColor(StdDraw.RED); 
StdDraw.polygon(x, y); 
rotate(x, y, theta); 
StdDraw.setPenColor(StdDraw.BLUE);
StdDraw.polygon(x, y);
    }
}
