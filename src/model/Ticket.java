package model;

import java.util.UUID;

public class Ticket {
	
	private UUID ticketnummer=UUID.randomUUID();
	

	private Passagier passagier;
	private Flug flug;
	private Sitzplatz sitzplatz;
	
	public Ticket(Passagier passagier, Flug flug, Sitzplatz sitzplatz) {
		super();
		
		this.passagier = passagier;
		this.flug = flug;
		this.sitzplatz = sitzplatz;
	}

	public UUID getTicketnummer() {
		return ticketnummer;
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
