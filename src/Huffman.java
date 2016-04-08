import java.util.*;

public class Huffman {
	
	HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
	String currentLine = "";

	
	public void findFreq(String inLine){
		System.out.println(inLine);
		int indexCounter=0;
		for (int i = 0; i < inLine.length(); i++){
		
			String currentChar=String.valueOf(inLine.charAt(indexCounter));
			
				if (!dictionary.keySet().contains(currentChar)){
					dictionary.put(currentChar, 1);
				}else{
					dictionary.replace(currentChar, dictionary.get(currentChar)+1);
				}
			
			indexCounter++;
		}
	}
	
	public void printhMap(){
		System.out.println("\n\nLETTER FREQUENCIES:\n===============\n");
		for (String keys:dictionary.keySet()){
			System.out.println("Letter: " + keys + "\tFreq: " + dictionary.get(keys));
		}
	}
}
