/*
 * The Node class handles the properties and creation of all nodes (character or otherwise)
 * for use in the Huffman class.
 * 
 * @author : Thomas Nix
 * @version : April 8th, 2016
 */
public class Node {
	
	/*toLeftNode is the child node to the left of the current node.
	 * toRightNode is the child node to the right of the current node.
	 * nodeLetter is the string representation of the character this node is acting on
	 * --NOTE: nodeLetter will be EMPTY for nodes created in the Huffman tree that are combinations of other nodes
	 * nodeFreq is the integer representation of the frequency of the current node.
	 * --NOTE: nodeFreq will be the sum of the frequencies of the child node(s) for any nodes created in the Huffman tree.
	 */
	Node toLeftNode, toRightNode;
	String nodeLetter="";
	int nodeFreq;
	
	public Node(Node nodeL, Node nodeR, String nodeSTR, int nodeFrequency){
		toLeftNode=nodeL;
		toRightNode=nodeR;
		nodeLetter=nodeSTR;
		nodeFreq=nodeFrequency;
	}
}
