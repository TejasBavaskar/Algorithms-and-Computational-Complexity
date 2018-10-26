package maxbottleneckrate;
import java.util.*; 


public class MaxBottleneckRate {
    private static int V=4; 
    int minKey(int key[], Boolean mstSet[]) 
    {  
        int min = Integer.MIN_VALUE, min_index=-1; 
        for (int v = 0; v < V; v++) 
        if (mstSet[v] == false && key[v] > min) 
        { 
            min = key[v]; 
            min_index = v; 
        } 
        return min_index; 
    } 
    void printMST(int parent[], int n, int graph[][]) 
    { 
        System.out.println("Edge \tWeight"); 
        for (int i = 1; i < V; i++) 
        System.out.println(parent[i]+" - "+ i+"\t"+ 
        graph[i][parent[i]]); 
    } 
    void primMST(int graph[][]) 
    { 
        int parent[] = new int[V];  
        int key[] = new int [V]; 
        Boolean mstSet[] = new Boolean[V]; 
        for (int i = 0; i < V; i++) 
        { 
            key[i] = Integer.MIN_VALUE; 
            mstSet[i] = false; 
        } 
        key[0] = 0;	 // Make key 0 so that this vertex is 
                                        // picked as first vertex 
        parent[0] = -1; // First node is always root of MST 

        // The MST will have V vertices 
        for (int count = 0; count < V-1; count++) 
        { 
                // Pick thd minimum key vertex from the set of vertices 
                // not yet included in MST 
                int u = minKey(key, mstSet); 

                // Add the picked vertex to the MST Set 
                mstSet[u] = true; 

                // Update key value and parent index of the adjacent 
                // vertices of the picked vertex. Consider only those 
                // vertices which are not yet included in MST 
                for (int v = 0; v < V; v++) 

                        // graph[u][v] is non zero only for adjacent vertices of m 
                        // mstSet[v] is false for vertices not yet included in MST 
                        // Update the key only if graph[u][v] is smaller than key[v] 
                        if (graph[u][v]!=0 && mstSet[v] == false && graph[u][v] > key[v]) 
                        { 
                                parent[v] = u; 
                                key[v] = graph[u][v]; 
                        } 
        } 

            // print the constructed MST 
        printMST(parent, V, graph); 
    } 
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of nodes: ");
        int num_nodes = scan.nextInt();
        V = num_nodes;
        MaxBottleneckRate t = new MaxBottleneckRate(); 
        int graph[][] = new int[][] {{0, 1, 4, 0,0}, 
                                    {1, 0, 6, 9,2}, 
                                    {4, 6, 0, 8,3}, 
                                    {0,9,8,0,0},
                                    {0,2,3,0,0}}; 
	t.primMST(graph); 
         //*************
        //finding shortest path from MST
        Graph g = new Graph(num_nodes);
        int num;
        System.out.println("*************************");
        for(int i=0;i<num_nodes;i++)
        {
            for(int j=0;j<num_nodes;j++)
            {
                if(i < j)
                {
                   System.out.print(i+ " to "+j+" :");
                    num = scan.nextInt();
                    if(num == 1)
                    {
                        g.addEdge(j, i);
                        g.addEdge(i, j);
                    } 
                }
            }
        }
        System.out.println("*************************");
        System.out.print("Enter Source: ");
        int s = scan.nextInt();
        System.out.print("Enter Destination: ");
        int d = scan.nextInt();
        
        System.out.println("Following are all different paths from "+s+" to "+d); 
        g.printAllPaths(s, d,graph); 

    }   
}


class Graph { 
 
	private int v; 
	private ArrayList<Integer>[] adjList; 
	public Graph(int vertices){ 
		this.v = vertices; 
		initAdjList(); 
	} 
	
	@SuppressWarnings("unchecked") 
	private void initAdjList() 
	{ 
		adjList = new ArrayList[v]; 
		
		for(int i = 0; i < v; i++) 
		{ 
			adjList[i] = new ArrayList<>(); 
		} 
	} 
	
	public void addEdge(int u, int v) 
	{ 
		adjList[u].add(v); 
	} 
 
	public void printAllPaths(int s, int d,int[][] graph) 
	{ 
		boolean[] isVisited = new boolean[v]; 
		ArrayList<Integer> pathList = new ArrayList<>(); 
		
		pathList.add(s); 
		printAllPathsUtil(s, d, isVisited, pathList,graph); 
	} 

	private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList,int[][] graph) 
        { 
		
            isVisited[u] = true; 
            int[] arr = new int[localPathList.size()];
            
            if (u.equals(d)) 
            { 
                System.out.println(localPathList);
                for(int i=0;i<localPathList.size();i++)
                {
                    arr[i] = localPathList.get(i);
                }
                for(int i=0;i<localPathList.size();i++)
                {
                    System.out.print(arr[i]+"\t");
                }

                findBottleneck(arr,graph);

            } 

            for (Integer i : adjList[u]) 
            { 
                if (!isVisited[i]) 
                { 
                    localPathList.add(i); 
                    printAllPathsUtil(i, d, isVisited, localPathList,graph); 
 
                    localPathList.remove(i); 
                } 
            } 

            isVisited[u] = false; 
	} 

    private void findBottleneck(int[] arr, int[][] graph) {
        int min = graph[arr[0]][arr[1]];
        for(int i=1;i<arr.length-1;i++) //size-1
        {
            if(min > graph[arr[i]][arr[i+1]])
            {
                min = graph[arr[i]][arr[i+1]];
            }
        }
        System.out.println("\nMin: "+ min);
    }
}
