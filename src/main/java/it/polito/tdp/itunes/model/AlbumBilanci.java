package it.polito.tdp.itunes.model;

public class AlbumBilanci implements Comparable<AlbumBilanci>{
	
	private Album a;
	private double bil;
	
	public AlbumBilanci(Album a, double bil) {
		super();
		this.a = a;
		this.bil = bil;
	}

	public Album getA() {
		return a;
	}

	public void setA(Album a) {
		this.a = a;
	}

	public double getBil() {
		return bil;
	}

	public void setBil(double bil) {
		this.bil = bil;
	}

	@Override
	public int compareTo(AlbumBilanci o) {
		return (int)(-(this.bil-o.bil));
	}

	@Override
	public String toString() {
		return a + "    bilancio = " + bil;
	}
	
	

}
