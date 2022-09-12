/*************************************************************************
 *  Compilation:  javac LargestOfFive.java
 *  Execution:    java LargestOfFive 35 10 32 1 8
 *
 *  @author:
 *
 *  Takes five distinct integers as command-line arguments and prints the 
 *  largest value
 *
 *
 *  % java LargestOfFive 35 10 32 1 8
 *  35
 *
 *  Assume the inputs are 5 distinct integers.
 *  Print only the largest value, nothing else.
 *
 *************************************************************************/

public class LargestOfFive {

    public static void main (String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);
        int e = Integer.parseInt(args[4]);

        if ((a >= b) && (a >= c) && (a >= d) && (a >= e)){
            System.out.println(a);
        }
        else
        if ((b >= a) && (b >= c) && (b >= d) && (b >= e)){
            System.out.println(b);
        }
        else
        if ((c >= b) && (c >= a) && (c >= d) && (c >= e)){
            System.out.println(c);
        }
        else
        if ((d >= b) && (d >= c) && (d >= a) && (d >= e)){
            System.out.println(d);
        }
        else
        if ((e >= b) && (e >= c) && (e >= d) && (e >= a)){
            System.out.println(e);
        }

    }
}