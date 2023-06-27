package it.polito.tdp.itunes.model;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	private Graph<Album, DefaultWeightedEdge> grafo;

	public Model() {
		super();
		this.dao = new ItunesDAO();
	}

	public void creaGrafo(int n) {
		
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, this.dao.getVertici(n));
		
		List<AlbumTrack> albumTrack = this.dao.getAlbumTrack(n);
		
		for(int i=0; i<=albumTrack.size(); i++) {
			for(int j=i+1; j<albumTrack.size(); j++) {
				if(albumTrack.get(i).getA().getAlbumId() != albumTrack.get(j).getA().getAlbumId()) {
					if(albumTrack.get(i).getnTracks() - albumTrack.get(j).getnTracks() < 0) {
						//i ha meno track di j --> arco da i a j
						this.grafo.addEdge(albumTrack.get(i).getA(), albumTrack.get(j).getA());
						this.grafo.setEdgeWeight(this.grafo.getEdge(albumTrack.get(i).getA(),
								albumTrack.get(j).getA()), albumTrack.get(j).getnTracks() - 
								albumTrack.get(i).getnTracks());
					}
					if(albumTrack.get(i).getnTracks() - albumTrack.get(j).getnTracks() > 0) {
						//i ha più tack di j --> arco da j a i
						this.grafo.addEdge(albumTrack.get(j).getA(), albumTrack.get(i).getA());
						this.grafo.setEdgeWeight(this.grafo.getEdge(albumTrack.get(j).getA(),
								albumTrack.get(i).getA()), albumTrack.get(i).getnTracks() - 
								albumTrack.get(j).getnTracks());
					}
				}
			}
		}
		
	}
	
	public List<AlbumBilanci> adiacenze(Album album){
		return this.calcolaBilanci(Graphs.successorListOf(grafo, album));
	}
	
	public List<AlbumBilanci> calcolaBilanci(List<Album> listaAlbum) {
		
		List<AlbumBilanci> res = new LinkedList<>();
		
		
		for(Album a : listaAlbum) {
			
			double in = 0.0;
			double out = 0.0;
			
			for(DefaultWeightedEdge e: this.grafo.incomingEdgesOf(a)) {
				in += this.grafo.getEdgeWeight(e);
			}
			
			for(DefaultWeightedEdge e: this.grafo.outgoingEdgesOf(a)) {
				out += this.grafo.getEdgeWeight(e);
			}
			
			res.add(new AlbumBilanci(a, in-out));
			
		}
		
		Collections.sort(res);
		
		return res;
		
	}

	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}

	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Album> getVertici() {
		
		List<Album> res = new LinkedList<>();
		
		for(Album a: this.grafo.vertexSet()) {
			res.add(a);
		}
		
		Collections.sort(res);
		
		return res;
	}
	
}
