package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Buchung implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private UUID buchungid = UUID.randomUUID();
    //public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Date buchungsdatum;
    private String Email;
    private String telefonnummer;
    private Flug flug;
    private Passagier passagier;
    private Ticket ticket;


    public Buchung(String Email, String telefonnummer, Flug flug, Passagier passagier,
    		Ticket ticket) {
    	
    	Calendar cal = new GregorianCalendar();
    	
    	this.setBuchungsdatum(cal.getTime());
    	this.setEmail(Email);
    	this.setTelefonnummer(telefonnummer);
    	this.setFlug(flug);
    	this.setPassagier(passagier);
    	this.setTicket(ticket);
    }

    public UUID getBuchungid() {
    	return buchungid;
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
	
	public String toString()
	{
		return "BuchungID: " + buchungid ;
				
	}

    
}
