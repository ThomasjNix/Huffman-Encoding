import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class driver {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner userInput = new Scanner (System.in);
		System.out.println("Enter a file name for a text file, do NOT include .txt at the end!");
		String userResponse = userInput.nextLine();
		
		File inFile = new File(userResponse.concat(".txt"));
		Scanner readFile = new Scanner(inFile);
		
		Huffman readIn = new Huffman();
		System.out.println("\nLines read:\n");
		while (readFile.hasNextLine()){
			readIn.findFreq(readFile.nextLine());
		}
		
		readIn.printhMap();
	}
}
