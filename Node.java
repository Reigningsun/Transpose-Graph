package hw4;

import java.util.ArrayList;

public class Node {
	int value;
	ArrayList <Node> adjNodes;
	ArrayList <Node> transposed;
	
	
	public Node(int value){
		this.value = value;
		this.adjNodes = new ArrayList <Node>();
		this.transposed = new ArrayList <Node>();
	}
	
	// ** Adds an out bound edge between current node and a target node to this nodes adjacency list **
	public void addAdj(Node adjNode){
		adjNodes.add(adjNode);
	}
}
