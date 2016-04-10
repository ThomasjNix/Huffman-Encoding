import java.util.*;

/*
 * This is the Huffman class that is responsible for all calculations done on the 
 * read in text file. It also produces the output for the user.
 * 
 * @author : Thomas Nix
 * @version : April 8th, 2016
 */
public class Huffman {
	
	//I chose to use HashMaps for this assignment.
	//The purpose of each HashMap:
	/*dictionary : Used for storing the unique letters and their frequencies
	 * xremainingNodes : Used for calculating the Huffman tree of nodes in regard to frequency
	 * associationMap : Used for storing the coding for each character
	 */
	//currentLine is used to hold each line passed in from the file in driver.java
	HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
	String currentLine = "";
	HashMap<Node, Integer> xremainingNodes = new HashMap<Node, Integer>();
	HashMap<String, String> associationMap = new HashMap<String, String>();
	
	/*
	 * The findFreq function is used to find the frequency of each letter in the file.
	 * It runs line by line and makes alterations where necessary.
	 * @param inLine : The current line passed in for the file (function called n times for number of lines in the file)
	 */
	public void findFreq(String inLine){
		System.out.println(inLine);
		int indexCounter=0;
		for (int i = 0; i < inLine.length(); i++){
			String currentChar=String.valueOf(inLine.charAt(indexCounter));
			if (currentChar.compareTo(" ")==0){
				currentChar="(space)";
			}
				if (!dictionary.keySet().contains(currentChar)){
					dictionary.put(currentChar, 1);
				}else{
					dictionary.replace(currentChar, dictionary.get(currentChar)+1);
				}
			indexCounter++;	
		}
	}

	/*
	 * The printhMap function prints the dictionary HashMap
	 * This includes all of the unique characters and their frequencies in the file.
	 */
	public void printhMap(){
		System.out.println("\n\nLETTER FREQUENCIES:\n===============\n");
		for (String keys:dictionary.keySet()){
			System.out.println("Letter: " + keys + "\tFreq: " + dictionary.get(keys));
		}
	}

	/*
	 * The initHTree function creates a HashMap for use in calculating the Huffman tree of nodes
	 * in regard to frequency. Initially all nodes have left and right node values set to null.
	 */
	public void initHTree(){
		System.out.println("\n\nAdding nodes to hmap:\n===============\n");
		for (String keys:dictionary.keySet()){
			Node leftNode = null;
			Node rightNode = null;
			xremainingNodes.put((new Node(leftNode, rightNode, keys, dictionary.get(keys))), dictionary.get(keys));
			System.out.println("Adding node: "+keys+" (weight: "+dictionary.get(keys)+")");
		}
		
		System.out.println("\n\nAll nodes in hmap:\n===============\n");
		for (Node keys:xremainingNodes.keySet()){
			System.out.println(keys.nodeLetter);
		}
		System.out.println("\n");
		
		System.out.println("Creating Huffman Coding Tree:\n===============\n");
		while (xremainingNodes.size()!=1){
			int min1=Integer.MAX_VALUE;
			int min2=Integer.MAX_VALUE;
			Node leftNodePostRemoval = null;
			Node rightNodePostRemoval = null;
	
			for (int i = 0; i < 2; i++){
				Node toBeRemoved = null;
				for (Node allNodes : xremainingNodes.keySet()){
		
					//for second min
					if (i==1){
						if (allNodes.nodeFreq<min2){
							min2=allNodes.nodeFreq;
							toBeRemoved=allNodes;
							leftNodePostRemoval=allNodes;
						}
					}else
					//for first min
					{
						if (allNodes.nodeFreq<min1){
							min1=allNodes.nodeFreq;
							toBeRemoved=allNodes;
							rightNodePostRemoval=allNodes;
						}
					}
				}
				xremainingNodes.remove(toBeRemoved);
			}
			xremainingNodes.put(new Node(leftNodePostRemoval, rightNodePostRemoval, "", min1+min2), min1+min2);
			System.out.println("Remaining nodes: "+xremainingNodes.size());
		}
		
		for (Node finalNodes:xremainingNodes.keySet()){
			System.out.println("\nCompleted:\n===============\nWeight of tree(root node): " + finalNodes.nodeFreq);
		}
		
		/*
		 * This was an attempt at using linked lists rather than a hashmap
		 * 
		while (remainingNodes.size()!=1){
			int min1=Integer.MAX_VALUE;
			int min2=Integer.MAX_VALUE;
			Node leftNodePostRemoval = null;
			Node rightNodePostRemoval = null;
			
			for (int i = 0; i < 2; i++){
				Node toBeRemoved=null;
				System.out.println("\n"+"Iteration:"+i);
				if(i == 0){
					ListIterator<Node> listIterator = remainingNodes.listIterator();
					while(listIterator.hasNext()){
						if (listIterator.next().nodeFreq<min1){
							Node tempNextNode=listIterator.previous();
							min1=tempNextNode.nodeFreq;
							toBeRemoved=tempNextNode;
							leftNodePostRemoval=tempNextNode;
						}
					}
					listIterator.remove();
				}
				if (i==1){
					ListIterator<Node> listIterator = remainingNodes.listIterator();
					System.out.println("Iteration2 running");
					while(listIterator.hasNext()){
						if (listIterator.next().nodeFreq<min2){
							Node tempNextNode=listIterator.previous();
							min2=tempNextNode.nodeFreq;
							toBeRemoved=tempNextNode;
							rightNodePostRemoval=tempNextNode;
						}
					}
					listIterator.remove();
				}
				
				System.out.println("Removing: "+toBeRemoved.nodeLetter + " weight: "+toBeRemoved.nodeFreq);
				
				
			}
			System.out.println("\nAdding node:\nLeft node: "+leftNodePostRemoval.nodeLetter+" Right node: "+
			rightNodePostRemoval.nodeLetter+"\nNew node weight: "+(min1+min2)+"\n");
			remainingNodes.add(new Node(leftNodePostRemoval, rightNodePostRemoval, "", min1+min2));
		}
		
		System.out.println(remainingNodes.get(0).nodeFreq);
		*/
	}
	
	/*
	 * The HuffmanEncoding function is responsible for handling the actual encoding of each node in the tree.
	 * This function recursively visits every left and right edge and assigns a '0' value for left edges
	 * and a '1' value for right edges.
	 * This function only assigns the coding when there are no paths left to visit, e.g
	 * the final child node (character) has been reached.
	 */
	public void HuffmanEncoding(Node startingNode, String encoding){
		if(startingNode.nodeLetter!=""){
			associationMap.put(startingNode.nodeLetter, encoding);
		}
		if(startingNode.toLeftNode!=null && startingNode.toRightNode!=null){
			HuffmanEncoding(startingNode.toLeftNode, encoding.concat("0"));
			HuffmanEncoding(startingNode.toRightNode, encoding.concat("1"));
		}
		else if(startingNode.toLeftNode==null && startingNode.toRightNode!=null){
			HuffmanEncoding(startingNode.toRightNode, encoding.concat("1"));
		}
		else if(startingNode.toLeftNode!=null && startingNode.toRightNode==null){
			HuffmanEncoding(startingNode.toRightNode, encoding.concat("0"));
		}
		else{
			System.out.println("Encoding complete for letter "+startingNode.nodeLetter);
		}
	}

	/*
	 * The getRootNode function is used AFTER the Huffman tree is calculated.
	 * It returns the root node of the calculated Huffman tree.
	 * @return rootNode : Returns the node at the root of the calculated Huffman tree
	 */
	public Node getRootNode(){
		Node rootNode=null;
		for(Node theOnlyNode:xremainingNodes.keySet()){
			rootNode=theOnlyNode;
		}
		return rootNode;
	}
	
	/*
	 * The printHuffmanEncoding function prints out the Huffman coding for each unique character
	 * read in from the file.
	 */
	public void printHuffmanCoding(){
		System.out.println("\n\nLETTER ENCODING:\n===============\n");
		for (String letter:associationMap.keySet()){
			System.out.println("Letter: " + letter + "\tEncoding: " + associationMap.get(letter));
		}
	}

	/*
	 * The runHuffman function calls all other functions in the Huffman class in proper order.
	 * This correctly produces the Huffman encoding for each unique character read from the file.
	 */
	public void runHuffman(){
		printhMap();
		initHTree();
		System.out.println("\n\nRunning Huffman Encoding algorithm...\n===============\n");
		HuffmanEncoding(getRootNode(), "");
		printHuffmanCoding();
	}
}
