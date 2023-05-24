package it.polito.tdp.metroparis.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.*;
import it.polito.tdp.metroparis.db.MetroDAO;

public class model {
	
	private Graph<Fermata, DefaultEdge> grafo;
	private List<Fermata> fermate;
	private Map<Integer, Fermata> fermateIdMap;
	
	
	public void creaGrafo() {
		//crea grafo
		this.grafo=new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungi vertici
		MetroDAO dao=new MetroDAO();
		this.fermate=dao.readFermate();
		
		fermateIdMap=new HashMap<>();
		for(Fermata f: this.fermate) {
			this.fermateIdMap.put(f.getIdFermata(), f);
		}
		Graphs.addAllVertices(this.grafo, fermate);
		System.out.println("Grafo creato con : "+ this.grafo.vertexSet().size()+" vertici e "+ this.grafo.edgeSet().size()+" archi");
		
		//aggiungi arco
	//--metodo 1: considero tutti i potenziali archi. 
//		for(Fermata partenza: this.grafo.vertexSet()) { //restituisce i vertici di tipo fermata
//			for(Fermata arrivo: this.grafo.vertexSet()) {
//				if(dao.isConnesse(partenza, arrivo)) {
//					this.grafo.addEdge(arrivo, partenza);
//					
//				}
//			}
//		}
//		System.out.println("connessioni di fermate: "+grafo.edgeSet().size());
		
		
	//--metodo 2: data uan fermata, trova la lista di quelle adiacenti
		for(Fermata partenza: this.grafo.vertexSet()) {
			List<Fermata> collegate=dao.trovaCollegamento(partenza, fermateIdMap);
			
			for(Fermata arrivo: collegate) {
				this.grafo.addEdge(partenza, arrivo);
			}
		}
		
	//--metodo2a: trovaIdCollegamento. restituisce tutte le stazioni collegate con la stazP data
		for(Fermata partenza: this.grafo.vertexSet()) {
			List<Fermata> collegate=dao.trovaIdCollegamento(partenza, fermateIdMap);
			
			for(Fermata arrivo: collegate) {
				this.grafo.addEdge(partenza, arrivo);
			}
		}
		
		
	//--metodo 3: coppiaF
		List<coppiaF> coppie=dao.getAllCoppie(fermateIdMap);
		for(coppiaF c: coppie){
			this.grafo.addEdge(c.getPartenza(), c.getArrivo());
		}
		
	}
	
	public List<Fermata> getAllFermate(){
		MetroDAO dao=new MetroDAO();
		return dao.readFermate();
	}
	
	public boolean isGradoLoaded() {
		return this.grafo.vertexSet().size()>0;
	}
	
	
	//determina il percorso minimo tra le 2 fermata
	public List<Fermata>percorso(Fermata partenza, Fermata arrivo) {
		//visita grafo partendo da partenza
		BreadthFirstIterator<Fermata, DefaultEdge> visita=new BreadthFirstIterator<>(this.grafo, partenza);
		
		List<Fermata> raggiungibili=new ArrayList<Fermata>();
		
		while(visita.hasNext()) {
			Fermata f=visita.next();
			//raggiungibili.add(f);
		}
		//System.out.println(raggiungibili);
		
		//trova il percorso sull'albero di visita
		List<Fermata> percorso=new ArrayList<>();
		Fermata corrente=arrivo;
		percorso.add(arrivo);
		
		DefaultEdge e=visita.getSpanningTreeEdge(corrente);
		while (e!=null) {
			Fermata precedente=Graphs.getOppositeVertex(this.grafo, e, corrente);
			percorso.add(0,precedente);
			corrente=precedente;
			
			e=visita.getSpanningTreeEdge(corrente);
		}
		
		return percorso;
		
	}
	
	
	
	
	
	
	
	
}





