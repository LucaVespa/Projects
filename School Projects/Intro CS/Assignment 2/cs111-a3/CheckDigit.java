/*************************************************************************
 *  Compilation:  javac CheckDigit.java
 *  Execution:    java CheckDigit 020131452
 *
 *  @author:
 *
 *  Takes a 12 or 13 digit integer as a command line argument, then computes
 *  and displays the check digit
 *
 *  java CheckDigit 048231312622
 *  0
 *
 *  java CheckDigit 9780470458310
 *  0
 * 
 *  java CheckDigit 9780470454310
 *  8
 * 
 *  Print only the check digit character, nothing else.
 *
 *************************************************************************/

public class CheckDigit {

    public static void main (String[] args) {

        long digit = Long.parseLong(args[0]);
        long left = digit/10;
        long right = digit;
        long rightsum = 0;
        long leftsum = 0;
        long rightmod = 0;
        long leftmod = 0;
        long sum = 0;
        while (right > 0){
            rightmod = right % 10;
            rightsum = rightmod + rightsum;
            right = right/100;
        }
        while (left > 0){
            
            leftmod = left % 10;
            leftsum = leftmod + leftsum;
            left = left/100;
        }
        rightsum = rightsum % 10;
        leftsum = leftsum % 10;
        leftsum = leftsum*3;
        leftsum = leftsum % 10;
        sum = leftsum + rightsum;
        sum = sum % 10;
        System.out.println(sum);




    }
}