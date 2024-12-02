/******************************************************************
 *
 *   SYDNEY AMAYA / SECTION 001
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor 
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */ 
  
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
             "Invalid vertex index: " + vertexIndex);
    }
  }


  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */ 
  
  public void printGraph() {
    System.out.println(
         "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }


  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * 
   */
  
  public int findRoot() {
    /**
     * 1.create an array to store the number of edges coming from each vertex
     * 2.use a for loop to to traverse each vertex's adj list (adjListArr[i])
     * 3.use an inner for loop to traverse each list and add to the array 
     * when an incoming edges (dest) is found
     * 4.use a for loop to check how many incoming edges each vertex has
     * 5.if a vertex has 0 incoming edges and foundRoot == -1, set foundRoot
     * to the vertex's value
     */
    int[] incomingEdges = new int [numVertices];
    int foundRoot = -1;
    for (int i = 0; i < numVertices; i++){
      //for each dest in the current list
      for (int dest : adjListArr[i]){
        incomingEdges[dest]++;
      }
    }
    for (int j = 0; j < numVertices; j++){
      //check if there are no incoming edges
      if (incomingEdges[j] == 0){
        //if != -1 then another root was already found 
        if(foundRoot != -1){
          return -1;
        }
        foundRoot = vertexValues.get(j);
      }
    }
    return foundRoot;
  } 
}
