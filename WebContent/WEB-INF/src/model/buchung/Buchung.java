package model.buchung;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;

public class Buchung implements Serializable {
	private String buchungid;
	private Date buchungsdatum;
	private String email;
	private String telefonnummer;
	private String flugcode;
	private ArrayList<Passagier> passagier;
	private ArrayList<Ticket> ticket;

	public Buchung(String buchungid, Date buchungsdatum, String email, String telefonnummer,
				   String flugcode, ArrayList<Passagier> passagier, ArrayList<Ticket> ticket) {
		this.buchungid = buchungid;
		this.buchungsdatum = buchungsdatum;
		this.email = email;
		this.telefonnummer = telefonnummer;
		this.flugcode = flugcode;
		this.passagier = passagier;
		this.ticket = ticket;
	}

	public String getBuchungid() {
		return buchungid;
	}

	public void setBuchungid(String buchungid) {
		this.buchungid = buchungid;
	}

	public Date getBuchungsdatum() {
		return buchungsdatum;
	}

	public void setBuchungsdatum(Date buchungsdatum) {
		this.buchungsdatum = buchungsdatum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public String getFlugcode() {
		return flugcode;
	}

	public void setFlugcode(String flugcode) {
		this.flugcode = flugcode;
	}

	public ArrayList<Passagier> getPassagier() {
		return passagier;
	}

	public void setPassagier(ArrayList<Passagier> passagier) {
		this.passagier = passagier;
	}

	public ArrayList<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(ArrayList<Ticket> ticket) {
		this.ticket = ticket;
	}
}
