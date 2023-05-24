package it.polito.tdp.metroparis;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import java.util.*;

public class ProvaGrafo {

	public static void main(String[] args) {
		Graph<String, DefaultEdge> grafo=new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		grafo.addVertex("r");
		grafo.addVertex("s");
		grafo.addVertex("t");
		grafo.addVertex("v");
		grafo.addVertex("w");
		grafo.addVertex("x");
		
		grafo.addEdge("r", "s");
		grafo.addEdge("r", "v");
		grafo.addEdge("s", "w");
		grafo.addEdge("t", "x");
		grafo.addEdge("t", "w");
		grafo.addEdge("w", "x");
		
		System.out.println(grafo.toString());
		System.out.println("Vertici: "+grafo.vertexSet().size());
		System.out.println("Archi: "+grafo.edgeSet().size());
		
		for(String i: grafo.vertexSet()) { 
			System.out.println("Vertice "+i+"  ha grado "+grafo.degreeOf(i));
			for(DefaultEdge arco: grafo.edgesOf(i)) { //restituisce tutti gli archi che incidono sull'arco i
				System.out.println(arco);
//				if(i.equals(grafo.getEdgeSource(arco))) {
//					String arrivo= grafo.getEdgeTarget(arco); //destinazione dell'arco
//					System.out.println("\tè connesso a "+ arrivo);
//				}else {
//					String arrivo= grafo.getEdgeSource(arco); //mittente dell'arco
//					System.out.println("\tè connesso a "+ arrivo);
//				}
				String arrivo= Graphs.getOppositeVertex(grafo, arco, i); //semplificazione del codide prima
				System.out.println("\t **è connesso a "+ arrivo);
			}
			List<String> vicini=Graphs.neighborListOf(grafo, i);
			System.out.println("Vicini: "+vicini);
		}
		
	}
}
