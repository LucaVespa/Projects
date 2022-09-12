/*************************************************************************
 *  Compilation:  javac RURottenTomatoes.java
 *  Execution:    java RURottenTomatoes
 *
 *  @author:
 *
 * RURottenTomatoes creates a 2 dimensional array of movie ratings 
 * from the command line arguments and displays the index of the movie 
 * that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) {

		// WRITE YOUR CODE HERE
		int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		int arr[][] = new int[m][n];

		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n; j++)
			{
				arr[i][j] = Integer.parseInt(args[2 + (n*i) + j]);
			}
		}
		int highest = 0;
		int index = 0;
		for (int i = 0; i < n; i++)
		{
			int sum = 0;
			for (int j = 0; j < m; j++)
			{
				sum = sum + arr[j][i];
			}
			if (sum > highest)
			{
				highest = sum;
				index = i;
			}
		}
		System.out.println(index);
	}
}
