package hw4;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
	
	
	
	// ** Transpose **																			// ** Adds a reversed edge towards the nodes predecessor **
	public static void transpose (ArrayList<Node> adjList, Node prevNode){
		int size = adjList.size();
		for (int i = 0; i < size; i++){															// ** Iterate through currently adjacent nodes **
			Node node = adjList.get(i);
			if (!node.transposed.contains(prevNode)){											// ** If an edge hasn't already been created add one pointing backwards **
				node.transposed.add(prevNode);
			}
			transpose(node.adjNodes, node);														// ** Recursively call transpose on all adjacent nodes from our current node
		}																						//    before continuing on to our next adjacent node **
	}

	
	// ** Performs a depth first search and prints out nodes in the order seen **
	public static void dfs (ArrayList <Node> adjList, ArrayList <Node> visited){
		int size = adjList.size();
		for (int i = 0; i < size; i++){															// ** Iterate through currently adjacent nodes **
			Node node = adjList.get(i);
			if (visited.contains(node)){														// ** If node has been seen previously then skip it **
				continue;
			} else {																			
				System.out.println(node.value);													// ** Print out the nodes value **
				visited.add(node);																// ** Mark the node as seen **
				dfs(node.adjNodes, visited);													// ** Recursively call DFS on all adjacent nodes from our current node
			}																					//    before continuing on to our next adjacent node **
		}
	}
	
	
	
	// ** Sorts nodes such that subsequent nodes never point back towards earlier nodes **
	public static ArrayList<Node> topoSort(ArrayList <Node> adjList, ArrayList <Node> visited2){
		Stack <Node> tempStack = new Stack<Node>();
		ArrayList <Node> sortedList = new ArrayList<Node>();
		int size = adjList.size();
		
		for (int i = 0; i < size; i++){																// ** Iterate through currently adjacent nodes **
			Node node = adjList.get(i);
			
			if (visited2.contains(node)){															// ** If node has been seen previously then skip it **
				continue;
			} else {
				visited2.add(node);																	// ** Mark the node as seen **
				ArrayList <Node> tempList = topoSort(node.adjNodes, visited2);						// ** Create a topologically sorted list for nodes adjacent to current node **
				for (int j = tempList.size() - 1; j >= 0; j--){										// ** Add sorted list into stack in reverse order to preserve order ** 
					tempStack.push(tempList.get(j));
				}
			}
			tempStack.push(node);																	// ** Add current node to stack so it appears before its successors **
		}
		
		while (!tempStack.isEmpty()){																// ** Organize into a list so it can be returned **
			Node currNode = tempStack.pop();
			sortedList.add(currNode);
		}
		return sortedList;
	}
	
	
	
	public static void main(String[] args) {
		
	// ** Setting up the Graph **
		
		// ** Creating the nodes **
		ArrayList <Node> adjList = new ArrayList<Node>();
		Node node38 = new Node(38);
		Node node50 = new Node(50);
		Node node3  = new Node(3);
		Node node4  = new Node(4);
		Node node5  = new Node(5);
		Node node6  = new Node(6);
		Node node7  = new Node(7);
		Node node8  = new Node(8);
		Node node9  = new Node(9);
		Node node24 = new Node(24);
		Node node56 = new Node(56);
		Node node15 = new Node(15);
		
		// ** Creating Adjacency Lists for the nodes **
		node38.addAdj(node4);
		node38.addAdj(node5);
		node50.addAdj(node4);
		node50.addAdj(node5);
		node50.addAdj(node6);
		node3.addAdj(node5);
		node3.addAdj(node7);
		node4.addAdj(node56);
		node5.addAdj(node56);
		node6.addAdj(node7);
		node6.addAdj(node8);
		node7.addAdj(node9);
		node7.addAdj(node24);
		node8.addAdj(node9);
		node8.addAdj(node24);
		node9.addAdj(node15);
		node24.addAdj(node15);
		
		// ** Adding the updated nodes to the adjList **
		adjList.add(node38);
		adjList.add(node50);
		adjList.add(node3);
		adjList.add(node4);
		adjList.add(node5);
		adjList.add(node6);
		adjList.add(node7);
		adjList.add(node8);
		adjList.add(node9);
		adjList.add(node24);
		
		
		
		
		// ** Testing the DFS method **
		System.out.println("DFS Search Results:");
		ArrayList <Node> visited = new ArrayList <Node>();
		dfs(adjList, visited);
		System.out.println("");


		
		// ** Testing the Topological sort method **
		System.out.println("Topologically Sorted Graph");
		ArrayList <Node> visited2 = new ArrayList <Node>();
		ArrayList <Node> sorted = topoSort(adjList, visited2);
		
		for (int i = 0; i < sorted.size(); i++){
			Node currNode = sorted.get(i);
			System.out.print(currNode.value + " ");
		}
		System.out.println(" ");
		
		
		
		// ** Testing the Transpose method **
		System.out.println(" ");
		System.out.println("Outward edges from node15 before transposing");
		if (node15.adjNodes.size() == 0){
			System.out.println("No outbound edges");
			System.out.println(" ");
		}
		for (int i = 0; i < node15.adjNodes.size(); i++){
			System.out.print(node15.adjNodes.get(i).value + " ");
		}
		System.out.println("Transposing Nodes in Graph");
		System.out.println("Current outward edges for node15: ");
		transpose(adjList, null);
		
		ArrayList <Node> trans = node15.transposed; 
		for (int i = 0; i < trans.size(); i++){
			Node current = trans.get(i);
			if (current == null){
				System.out.println("No outbound edges");
				break;
			}
			System.out.print(current.value + " ");
		}
	}
}
