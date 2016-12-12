package model.buchung;

import java.io.Serializable;
import java.util.Date;

public class Buchung implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int buchungid;
    //public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Date buchungsdatum;
    private String Email;
    private String telefonnummer;
    private Flug flug;
    private Passagier passagier;
    private Ticket ticket;


    public Buchung(int buchungid, Date buchungsdatum, String Email, String telefonnummer, Flug flug, Passagier passagier,
    		Ticket ticket) {
    	this.setBuchungid(buchungid);
    	this.setBuchungsdatum(buchungsdatum);
    	this.setEmail(Email);
    	this.setTelefonnummer(telefonnummer);
    	this.setFlug(flug);
    	this.setPassagier(passagier);
    	this.setTicket(ticket);
    }

    public int getBuchungid() {
    	return buchungid;
    }
    
    public void setBuchungid(int buchungid){
    	if (buchungid!= (Integer) buchungid) throw new IllegalArgumentException("Die Buchungid muss Integer sein");
    	else this.buchungid = buchungid;
    }
    
    public Date getBuchungsdatum() {
    	return buchungsdatum;
    }
    
    public void setBuchungsdatum(Date buchungsdatum){
    	if (buchungsdatum!= (Date) buchungsdatum) throw new IllegalArgumentException("Das Buchungsdatum muss Date sein");
    	this.buchungsdatum = buchungsdatum;
    }
    

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		if (email.isEmpty() ) throw new IllegalArgumentException("Email is empty!");
		else this.Email = email;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		if (telefonnummer.isEmpty() ) throw new IllegalArgumentException("Telefonnummer is empty!");
		else
		this.telefonnummer = telefonnummer;
	}

	public Flug getFlug() {
		return flug;
	}

	public void setFlug(Flug flug) {
		this.flug = flug;
	}

	public Passagier getPassagier() {
		return passagier;
	}

	public void setPassagier(Passagier passagier) {
		this.passagier = passagier;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

    
}
