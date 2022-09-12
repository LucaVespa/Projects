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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
        // java -cp bin prereqchecker.NeedToTake adjlist.in needtotake.in needtotake.out

    StdIn.setFile(args[0]);
    StdOut.setFile(args[2]);
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
    //////////////////// AdjList Done /////////////////////////
    StdIn.setFile(args[1]);   
    int [] marked = new int[numOfCourses];
    String targetCourse = StdIn.readString();
    int totalTaken = StdIn.readInt();
    for (int i = 0; i < numOfCourses; i++){
        marked[i] = 0;
    }
    for (int i = 0; i < totalTaken; i++){
        String course = StdIn.readString();
        int z = traverse(main, course);
        if (marked[z] != 1){
        dfs(main, marked, course);
        }
        
    }  
    dfs2(main, marked, targetCourse);
    for (int q = 0; q < numOfCourses; q++){
        if (marked[q] == 2 && !main.get(q).get(0).equals(targetCourse)){
            StdOut.println(main.get(q).get(0));
        }
    }
    }

    //////////////////// Additional Methods ///////////////////////

    public static int traverse(ArrayList<ArrayList<String>> a, String s){ // returns ArrayList index when the target value is the root
        int j = -1;
        for (int i = 0; i < a.size(); i++){
            if (a.get(i).get(0).equals(s)){
                j = i;
            }
        }
        return j;
    }

    public static void dfs(ArrayList<ArrayList<String>> a, int [] m, String s){
        int k = traverse(a, s);
        m[k] = 1;

        int q = 0;
        for (int j = 0; j < a.get(k).size(); j++){ 
            q = traverse(a, a.get(k).get(j));
            if (m[q] == 0){ // if the node is not marked, call the new node as a root
                dfs(a, m, a.get(q).get(0));
            }
        }

    }
    public static void dfs2(ArrayList<ArrayList<String>> a, int [] m, String s){
        int k = traverse(a, s);
        m[k] = 2;

        int q = 0;
        for (int j = 0; j < a.get(k).size(); j++){ 
            q = traverse(a, a.get(k).get(j));
            if (m[q] == 0){ // if the node is not marked, call the new node as a root
                dfs2(a, m, a.get(q).get(0));
            }
        }

    }


}
