import java.io.*;
import java.util.*;
//----------------------------------------------------------
//Obtenido de: https://esacademic.com/dic.nsf/eswiki/604316#Forma_de_compilar_y_ejecutar
class MatrizAdyacencia{
	long[][] matriz = {{1,0,50,30,15},{2,5,0,9,9},{3,9,15,0,5},{4,9,5,15,0}};
	public long[][] getMatriz() {
		return matriz;
	}
	public void setMatriz(long[][] matriz) {
		this.matriz = matriz;
	}
}
class Warshall{
public String floyd(long[][] adyacencia){
	int n=adyacencia.length;
    long D[][]=adyacencia;
    String enlaces[][]=new String [n][n];
    String[][] aux_enlaces=new String[adyacencia.length][adyacencia.length]; 
    for (int i = 0; i < n; i++) {
    	for (int j = 0; j < n; j++) {
    		enlaces[i][j]="";
            aux_enlaces[i][j]="";
            }
         }
    String enl_rec="";
    for (int k = 0; k < n; k++) {
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			float aux=D[i][j];
    			float aux2=D[i][k];
    			float aux3=D[k][j];
    			float aux4=aux2+aux3;
    			float res=Math.min(aux,aux4);
    			if(aux!=aux4)
    			{
    				if(res==aux4)
    				{
    					enl_rec="";
    					aux_enlaces[i][j]=k+"";
    					enlaces[i][j]=enlaces(i,k,aux_enlaces,enl_rec)+(k+1);
    				}
    			}
    			D[i][j]=(long) res;
    		}
    	}
    }
    String cadena="";
    for (int i = 0; i < n; i++) {
    	for (int j = 0; j < n; j++) {
    		cadena+=D[i][j]+" ";
    	}
    	cadena+="\n";
    }
    String enlacesres="";
    	for (int i = 0; i <n; i++) {
    		for (int j = 0; j < n; j++) {
    			if(i!=j)
    			{
    				if(enlaces[i][j].equals("")==true)
    				{
    					enlacesres+=" De ( "+(i+1)+" a "+(j+1)+" ) = "+"( "+(i+1)+" , "+(j+1)+" )"+"\n";
    				}
    				else
    					enlacesres+=" De ( "+(i+1)+" a "+(j+1)+" ) = ( "+(i+1)+" , "+enlaces[i][j]+" , "+(j+1)+")\n";
    			}
    		}
    	}
    	return "las distancias minimas entre nodos son: \n"+cadena+"\nlos caminos minimos entre nodos son:\n"+enlacesres;
}
public String enlaces(int i,int k,String[][] aux_enlaces,String enl_rec)
{
	if(aux_enlaces[i][k].equals("")==true)
	{
		return "";
		}
	else
	{
 enl_rec+=enlaces(i,Integer.parseInt(aux_enlaces[i][k].toString()),aux_enlaces,enl_rec)+(Integer.parseInt(aux_enlaces[i][k].toString())+1)+" , ";
 return enl_rec;
	}
}
}

//---------------------------------------------------------------------------
//Java implementation of Dijkstra's Algorithm  
//using Priority Queue 

//Class to represent a node in the graph 
class Node implements Comparator<Node> { 
public int node; 
public int cost; 

public Node() 
{ 
} 

public Node(int node, int cost) 
{ 
   this.node = node; 
   this.cost = cost; 
} 

@Override
public int compare(Node node1, Node node2) 
{ 
   if (node1.cost < node2.cost) 
       return -1; 
   if (node1.cost > node2.cost) 
       return 1; 
   return 0; 
} 
} 


public class Prueba {
	private int dist[]; 
	private Set<Integer> settled; 
	private PriorityQueue<Node> pq; 
	private int V; // Number of vertices 
	List<List<Node> > adj; 

	public Prueba(int V) 
	{ 
	   this.V = V; 
	   dist = new int[V]; 
	   settled = new HashSet<Integer>(); 
	   pq = new PriorityQueue<Node>(V, new Node()); 
	} 

	// Function for Dijkstra's Algorithm 
	public void dijkstra(List<List<Node> > adj, int src) 
	{ 
	   this.adj = adj; 

	   for (int i = 0; i < V; i++) 
	       dist[i] = Integer.MAX_VALUE; 

	   // Add source node to the priority queue 
	   pq.add(new Node(src, 0)); 

	   // Distance to the source is 0 
	   dist[src] = 0; 
	   while (settled.size() != V) { 

	       // remove the minimum distance node  
	       // from the priority queue  
	       int u = pq.remove().node; 

	       // adding the node whose distance is 
	       // finalized 
	       settled.add(u); 

	       e_Neighbours(u); 
	   } 
	} 

	// Function to process all the neighbours  
	// of the passed node 
	private void e_Neighbours(int u) { 
	   int edgeDistance = -1; 
	   int newDistance = -1; 

	   // All the neighbors of v 
	   for (int i = 0; i < adj.get(u).size(); i++) { 
	       Node v = adj.get(u).get(i); 

	       // If current node hasn't already been processed 
	       if (!settled.contains(v.node)) { 
	           edgeDistance = v.cost; 
	           newDistance = dist[u] + edgeDistance; 

	           // If new distance is cheaper in cost 
	           if (newDistance < dist[v.node]) 
	               dist[v.node] = newDistance; 

	           // Add the current node to the queue 
	           pq.add(new Node(v.node, dist[v.node])); 
	       } 
	   } 
	} 
	
//----------------------- Obtenido de: Anónimo. (15/05/2020). Algoritmo Bellman Ford (implementación simple). 18/12/2020, de geeksforgeeks Sitio web: https://www.geeksforgeeks.org/bellman-ford-algorithm-simple-implementation/
	
	// The main function that finds shortest 
	// distances from src to all other vertices 
	// using Bellman-Ford algorithm. The function 
	// also detects negative weight cycle 
	// The row graph[i] represents i-th edge with 
	// three values u, v and w. 
	static void BellmanFord(int graph[][], int V, int E, int src) { 
	    // Initialize distance of all vertices as infinite. 
	    int []dis = new int[V]; 
	    for (int i = 0; i < V; i++) 
	        dis[i] = Integer.MAX_VALUE; 
	  
	    // initialize distance of source as 0 
	    dis[src] = 0; 
	  
	    // Relax all edges |V| - 1 times. A simple 
	    // shortest path from src to any other 
	    // vertex can have at-most |V| - 1 edges 
	    for (int i = 0; i < V - 1; i++)  
	    { 
	  
	        for (int j = 0; j < E; j++)  
	        { 
	            if (dis[graph[j][0]] + graph[j][2] < 
	                            dis[graph[j][1]]) 
	                dis[graph[j][1]] =  
	                dis[graph[j][0]] + graph[j][2]; 
	        } 
	    } 
	  
	    // check for negative-weight cycles. 
	    // The above step guarantees shortest 
	    // distances if graph doesn't contain 
	    // negative weight cycle. If we get a 
	    // shorter path, then there is a cycle. 
	    for (int i = 0; i < E; i++){ 
	        int x = graph[i][0]; 
	        int y = graph[i][1]; 
	        int weight = graph[i][2]; 
	        if (dis[x] != Integer.MAX_VALUE && 
	                dis[x] + weight < dis[y]) 
	            System.out.println("Graph contains negative"
	                    +" weight cycle"); 
	    } 
	  
	    System.out.println("Vertex Distance from Source"); 
	    for (int i = 0; i < V; i++) 
	        System.out.println(i + "\t\t" + dis[i]); 
	} 
	  	
//--------------------------
	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		boolean menu=false;
		String op="";
		System.out.println("--------Examen segunda oprtunidad Rafael Eulalio Villaneda de la Torre------------------");
		do {
			System.out.println("1- Algoritmo DIJKSTRA");
			System.out.println("2- Algoritmo FLOYD-WARSHAL");
			System.out.println("3- Algoritmo BELLMAN-FORD.");
			System.out.println("4- Salir");
			op=entrada.nextLine();
			switch (op) {
			case "1":
			   int V = 5; 
			   int source = 0; 

			   // Adjacency list representation of the  
			   // connected edges 
			   List<List<Node> > adj = new ArrayList<List<Node> >(); 

			   // Initialize list for every node 
			   for (int i = 0; i < V; i++) { 
			       List<Node> item = new ArrayList<Node>(); 
			       adj.add(item); 
			   } 

			   // Inputs for the DPQ graph 
			   adj.get(0).add(new Node(1, 9)); 
			   adj.get(0).add(new Node(2, 6)); 
			   adj.get(0).add(new Node(3, 5)); 
			   adj.get(0).add(new Node(4, 3));

			   adj.get(2).add(new Node(1, 2)); 
			   adj.get(2).add(new Node(3, 4)); 

			   // Calculate the single source shortest path 
			   Prueba dpq = new Prueba(V); 
			   dpq.dijkstra(adj, source); 

			   // Print the shortest path to all the nodes 
			   // from the source node 
			   System.out.println("The shorted path from node :"); 
			   for (int i = 0; i < dpq.dist.length; i++) 
			       System.out.println(source + " to " + i + " is "
			                          + dpq.dist[i]); 
				break;
			case "2":
				MatrizAdyacencia matriz = new MatrizAdyacencia();
				Warshall warsh = new Warshall();
				System.out.println(warsh.floyd(matriz.getMatriz()));
				break;
			case "3":
				int V1 = 5; // Number of vertices in graph 
			    int E = 8; // Number of edges in graph 
			  
			    // Every edge has three values (u, v, w) where 
			    // the edge is from vertex u to v. And weight 
			    // of the edge is w. 
			    int graph[][] = { { 0, 1, -1 }, { 0, 2, 4 }, 
			                    { 1, 2, 3 }, { 1, 3, 2 },  
			                    { 1, 4, 2 }, { 3, 2, 5 },  
			                    { 3, 1, 1 }, { 4, 3, -3 } }; 
			  
			    BellmanFord(graph, V1, E, 0); 
				break;
			case "4":
				System.out.println("Saliendo....");
				menu=true;
				break;
			default:
				break;
			}
		}while(menu==false);

	}

}
