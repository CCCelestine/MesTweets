import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class graphes {
		public static void main(String args[]) {
			Graph graph = new SingleGraph("Tutorial 1");
			
			graph.addNode("A" );
			graph.addNode("B" );
			graph.addNode("C" );
			graph.addNode("D");
			graph.addNode("E");
			graph.addEdge("AB", "A", "B");
			graph.addEdge("BC", "B", "C");
			graph.addEdge("CA", "C", "A");
			graph.addEdge("DA", "D", "A");
			graph.addEdge("DE", "D", "E");
			
			
			graph.display(); //affichage


		}
	}


