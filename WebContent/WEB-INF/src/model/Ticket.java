package model;

import java.io.Serializable;

public class Ticket implements Serializable {
	
	private String ticketnummer;
	private String passnr;
	private String flugcode;
	private int[] sitzplatznr = new int[2]; //[0]-spalte, [1]-zeile

	public Ticket(String ticketnummer, String passnr, String flugcode, int sitzplatz_spalte, int sitzplatz_zeile) {
		this.ticketnummer = ticketnummer;
		this.passnr = passnr;
		this.flugcode = flugcode;
		this.sitzplatznr[0] = sitzplatz_spalte;
		this.sitzplatznr[1] = sitzplatz_zeile;
	}

	public String getTicketnummer() {
		return ticketnummer;
	}

	public void setTicketnummer(String ticketnummer) {
		this.ticketnummer = ticketnummer;
	}

	public String getPassnr() {
		return passnr;
	}

	public void setPassnr(String passnr) {
		this.passnr = passnr;
	}

	public String getFlugcode() {
		return flugcode;
	}

	public void setFlugcode(String flugcode) {
		this.flugcode = flugcode;
	}

	public int[] getSitzplatznr() {
		return sitzplatznr;
	}

	public void setSitzplatznr(int[] sitzplatznr) {
		this.sitzplatznr = sitzplatznr;
	}
}
