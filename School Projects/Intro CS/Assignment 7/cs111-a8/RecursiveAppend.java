/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author:
 *
 *************************************************************************/

public class RecursiveAppend {

    // Returns the original string appended to the original string n times 
    public static String appendNTimes (String original, int n) {

	// WRITE YOUR CODE HERE
     String a = new String (original);
  
    if (n == 0){
        return original;
    }
    else{
        return original + appendNTimes(original, n - 1);
    }
    
    }

    public static void main (String[] args) {
        
    StdOut.println(appendNTimes("cat", 5));
    }
}
