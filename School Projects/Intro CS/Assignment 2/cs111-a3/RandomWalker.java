/*************************************************************************
 *  Compilation:  javac RandomWalker.java
 *  Execution:    java RandomWalker 10
 *
 *  @author:
 *
 * The program RandomWalker that takes an int command-line argument n
 * and simulates the motion of a random walk for n steps. Print the
 * location at each step (including the starting point), treating the
 * starting point as the origin (0, 0). Also, print the square of the
 * final Euclidean distance from the origin.
 *
 *  % java RandomWalker 10
 * (0,0)
 * (-1,0)
 * (-1,-1)
 * (-1,-2)
 * (-1,-3)
 * (-1,-4)
 * (-1,-5)
 * (0,-5)
 * (-1,-5)
 * (-2,-5)
 * (-2,-4)
 * Squared distance = 20.0
 *
 *************************************************************************/

public class RandomWalker {

    public static void main(String[] args) {

	int n = Integer.parseInt(args[0]);
    double rand = 0;
    int x = 0;
    int y = 0;
    int sumx = 0;
    int sumy = 0;
    System.out.println("(" + sumx + "," + sumy + ")");

    while (n > 0){
    rand = Math.random();
    if (rand > 0.75){
        x = 1;
        y = 0;

    }
    else
    if (rand > 0.50){
         x = -1;
         y = 0;

    }
    else
    if (rand > 0.25){

        y = -1;
        x = 0;

    }
    else{

        y = 1;
        x = 0;

    }
    sumx = sumx + x;
    sumy = sumy + y;
    System.out.println("(" + sumx + "," + sumy + ")");

    n = n - 1;
    }

    double xsq = sumx*sumx;
    double ysq = sumy*sumy;
    double distancesq = xsq + ysq;
 

    System.out.println(distancesq);


   

    }
}
