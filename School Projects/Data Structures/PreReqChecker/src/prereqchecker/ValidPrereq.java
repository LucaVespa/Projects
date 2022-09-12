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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE
        // java -cp bin prereqchecker.ValidPrereq adjlist.in validprereq.in validprereq.out

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
    /* for (int i = 0; i < main.size(); i++){
        for (int j = 0; j < main.get(i).size(); j++){
            StdOut.print(main.get(i).get(j) + " ");
        }
        StdOut.println();
    }*/

    //////////////////// AdjList Done /////////////////////////
    StdIn.setFile(args[1]);
    int [] marked = new int[numOfCourses];
    for (int i = 0; i < numOfCourses; i++){
        marked[i] = 0;
    }
    String course1 = StdIn.readString();
    String course2 = StdIn.readString();
        dfs(main, marked, course2);
    int g = traverse(main, course1);
    if (marked[g] == 0){
        StdOut.print("YES");
    }
    else{
        StdOut.print("NO");
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
    }

