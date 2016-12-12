package model;

public class Ticket {
	
	private int ticketnummer;
	

	private Passagier passagier;
	private Flug flug;
	private Sitzplatz sitzplatz;
	
	public Ticket(int ticketnummer, Passagier passagier, Flug flug, Sitzplatz sitzplatz) {
		super();
		this.ticketnummer = ticketnummer;
		this.passagier = passagier;
		this.flug = flug;
		this.sitzplatz = sitzplatz;
	}

	public int getTicketnummer() {
		return ticketnummer;
	}

	public void setTicketnummer(int ticketnummer) {
		if (ticketnummer!= (Integer) ticketnummer) throw new IllegalArgumentException("Ticketnummer muss Integer sein");
    	else
		this.ticketnummer = ticketnummer;
	}

	public Passagier getPassagier() {
		return passagier;
	}

	public void setPassagier(Passagier passagier) {
		this.passagier = passagier;
	}

	public Flug getFlug() {
		return flug;
	}

	public void setFlug(Flug flug) {
		this.flug = flug;
	}

	public Sitzplatz getSitzplatz() {
		return sitzplatz;
	}

	public void setSitzplatz(Sitzplatz sitzplatz) {
		this.sitzplatz = sitzplatz;
	}
	
	
}
