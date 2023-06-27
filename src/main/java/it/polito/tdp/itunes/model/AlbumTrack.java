package it.polito.tdp.itunes.model;

import java.util.Objects;

public class AlbumTrack {
	
	private Album a;
	private int nTracks;
	
	public AlbumTrack(Album a, int nTracks) {
		super();
		this.a = a;
		this.nTracks = nTracks;
	}

	public Album getA() {
		return a;
	}

	public void setA(Album a) {
		this.a = a;
	}

	public int getnTracks() {
		return nTracks;
	}

	public void setnTracks(int nTracks) {
		this.nTracks = nTracks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, nTracks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumTrack other = (AlbumTrack) obj;
		return Objects.equals(a, other.a) && nTracks == other.nTracks;
	}
	
	

}
