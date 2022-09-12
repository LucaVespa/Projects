package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        StdIn.setFile(fileName);

	/* Your code goes here */
        
    ArrayList<Character> chars = new ArrayList<Character>();
    //ArrayList<Integer> skips = new ArrayList<Integer>();
    ArrayList<CharFreq> arr = new ArrayList<CharFreq>();
    int [] list = new int [128];
   

    while (StdIn.hasNextChar() == true){
        chars.add(StdIn.readChar());
    }
    int total = chars.size();

    for(int i = 0; i < chars.size(); i++){
       list[(int)(chars.get(i))] += 1;
    }
   
    for(int i = 0; i < list.length; i++){
        if(list[i] != 0){
        CharFreq n = new CharFreq((char) i, list[i]);
        arr.add(n);
        }
    }

    if(arr.size() < 2){
        if(arr.get(0).getCharacter() == 127){
            arr.add(new CharFreq((char) 0, 0));
        }else{
            arr.add(new CharFreq((char) (arr.get(0).getCharacter() + 1), 0));
        }
        }

    Collections.sort(arr);

    for(int i = 0; i < arr.size(); i++){
        arr.get(i).setProbOcc((double) (arr.get(i).getProbOcc())/total);
    }

    sortedCharFreqList = arr;

        }

    

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

	/* Your code goes here */
    
    Queue<TreeNode> source = new Queue<TreeNode>();
    Queue<TreeNode> target = new Queue<TreeNode>();
    Queue<TreeNode> dequeue = new Queue<TreeNode>();
       
    for(int i = 0; i < sortedCharFreqList.size(); i++){
        TreeNode n = new TreeNode(sortedCharFreqList.get(i), null, null);
        source.enqueue(n);
    }
        while((source.isEmpty() == false) || (target.size() != 1)){
            while(dequeue.size() < 2){
                if(target.isEmpty() == true){
                    dequeue.enqueue(source.dequeue());
                }
                else{
                    if(source.isEmpty() == false){
                        if(source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()){
                            dequeue.enqueue(source.dequeue());
                        }
                        else{
                            dequeue.enqueue(target.dequeue());
                        }
                    }
                    else{
                        dequeue.enqueue(target.dequeue());
                    }
                }
            }

            TreeNode small = new TreeNode();
            TreeNode small2 = new TreeNode();
            if(dequeue.isEmpty() == true){
                small = null;
            }
            else{
                small = dequeue.dequeue();
            }
            if(dequeue.isEmpty() == true){
                small2 = null;
            }
            else{
                small2 = dequeue.dequeue();
            }
            double prob1;
            double prob2;

            if(small.getData() == null){
                prob1 = 0;
            }
            else{
                prob1 = small.getData().getProbOcc();
            }
            if(small2.getData() == null){
                prob2 = 0;
            }
            else{
                prob2 = small2.getData().getProbOcc();
            }
            CharFreq temp = new CharFreq(null, prob1 + prob2);
            TreeNode base = new TreeNode(temp, small, small2);
            target.enqueue(base);

            
    }
    huffmanRoot = target.peek();
}
    
        

    
    
    

    

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {

	/* Your code goes here */
        String array [] = new String [128];
        char n = 'h';
        inorder(huffmanRoot, "", array, n);
        encodings = array;
    }

    private void inorder(TreeNode x, String q, String [] t, char n) { 

        
        if ((x == null)){
            q = q.substring(0, q.length()-1);
            t[(int) n] = q;
         return;
        } 
        if ((x.getData().getCharacter() != null)){
        n = x.getData().getCharacter();
        }
        inorder(x.getLeft(), q + "0", t, n);
        inorder(x.getRight(), q + "1", t, n);
        
        }
    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);

	/* Your code goes here */

    ArrayList<Character> chars = new ArrayList<Character>();
    ArrayList<String> stringList = new ArrayList<String>();
    String bitString = "";
    while (StdIn.hasNextChar() == true){
        chars.add(StdIn.readChar());
    }

    for(int i = 0; i < chars.size(); i++){
        stringList.add((String) encodings[(int) chars.get(i)]);
    }
    for(int i = 0; i < stringList.size(); i++){
        bitString = bitString + stringList.get(i);
    }

        //StdOut.print(bitString);
        writeBitString(encodedFile, bitString);

    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);

        
	/* Your code goes here */


        String bitString = readBitString(encodedFile);
        String bitCheck = "";
        String product = "";
        char[] charArray = bitString.toCharArray(); 

        for (int i = 0; i < charArray.length; i ++){
            bitCheck = bitCheck + charArray[i];
            
            for (int j = 0; j < encodings.length; j++){
                if(bitCheck.equals(encodings[j])){
                    bitCheck = "";
                    product = product + (char) j;
                }
            }
            
                    
                
        }
        StdOut.print(product);
        
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
