import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

/*
 * @author : Thomas Nix
 * @version : April 8th, 2016
 */
public class driver {

	/*
	 * This is the main class that reads in the text file and calls 
	 * functions of the Huffman class to calculate and produce output.
	 */
	public static void main(String[] args) throws FileNotFoundException{
		//Scanner and File for createHEObjectg in the text file
		Scanner userInput = new Scanner (System.in);
		System.out.println("Enter a file name for a text file, do NOT include .txt at the end!\n"
				+ "This file MUST be in the proper directory.");
		String userResponse = userInput.nextLine();
		File inFile = new File(userResponse.concat(".txt"));
		Scanner readFile = new Scanner(inFile);
		
		//Reads the line and passes lines of text file to the Huffman class.
		Huffman createHEObject = new Huffman();
		System.out.println("\nLines read:\n===============\n");
		while (readFile.hasNextLine()){
			createHEObject.findFreq(readFile.nextLine());
		}
		
		//This function calls all of the other functions in the Huffman class
		//It runs all calculations and returns the proper values.
		createHEObject.runHuffman();
	}
}
