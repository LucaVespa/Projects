package prereqchecker;


import java.util.*;
/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    StdIn.setFile(args[0]);
    StdOut.setFile(args[1]);
    int numOfCourses = StdIn.readInt();

    
    ArrayList<ArrayList<String> > main = new ArrayList<ArrayList<String>>();

    for (int i = 0; i < numOfCourses; i++){
        ArrayList<String> n = new ArrayList<String>();
        main.add(n);
    }

    for (int i = 0; i < numOfCourses; i++){
        main.get(i).add(StdIn.readString());
    }

    int numOfEdges = StdIn.readInt();
    for (int i = 0; i < numOfEdges ; i++){
        String higherLvlClass = StdIn.readString();
        String lowerLvlClass = StdIn.readString();
        for (int j = 0; j < main.size(); j++){
            if(main.get(j).get(0).equals(higherLvlClass)){
                main.get(j).add(lowerLvlClass);
            }
        }
    }
    for (int i = 0; i < main.size(); i++){
        for (int j = 0; j < main.get(i).size(); j++){
            StdOut.print(main.get(i).get(j) + " ");
        }
        StdOut.println();
    }

    }



    public static int traverse(ArrayList<ArrayList<String>> a, String s){
        return 1;
    }



    ///////////////////////////////////////////
}
