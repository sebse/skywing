package model;

import java.util.Date;
import java.io.Serializable;

public class Passagier implements Serializable {
	
 private String vorname;
 private String nachname;
 private String strasse;
 private String ort;
 private String postleitzahl;
 private Date geburtsdatum;
 private String passnummer;
 private Date passAblaufsdatum;
 private String nationalitaet;
 
public Passagier(String vorname, String nachname, String strasse, String ort, String postleitzahl, Date geburtsdatum,
		String passnummer, Date passAblaufsdatum, String nationalitaet) {
	super();
	this.vorname = vorname;
	this.nachname = nachname;
	this.strasse = strasse;
	this.ort = ort;
	this.postleitzahl = postleitzahl;
	this.geburtsdatum = geburtsdatum;
	this.passnummer = passnummer;
	this.passAblaufsdatum = passAblaufsdatum;
	this.nationalitaet = nationalitaet;
}

public String getVorname() {
	return vorname;
}

public void setVorname(String vorname) {
	if (vorname.isEmpty() ) throw new IllegalArgumentException("Vorname is empty!");
	else 
	this.vorname = vorname;
}

public String getNachname() {
	return nachname;
}

public void setNachname(String nachname) {
	if (nachname.isEmpty() ) throw new IllegalArgumentException("Nachname is empty!");
	else 
	this.nachname = nachname;
}

public String getStrasse() {
	return strasse;
}

public void setStrasse(String strasse) {
	if (strasse.isEmpty() ) throw new IllegalArgumentException("Strasse is empty!");
	else 
	this.strasse = strasse;
}

public String getOrt() {
	return ort;
}

public void setOrt(String ort) {
	if (ort.isEmpty() ) throw new IllegalArgumentException("Ort is empty!");
	else 
	this.ort = ort;
}

public String getPostleitzahl() {
	return postleitzahl;
}

public void setPostleitzahl(String postleitzahl) {
	if (postleitzahl.isEmpty() ) throw new IllegalArgumentException("Postleitzahl is empty!");
	else 
	this.postleitzahl = postleitzahl;
}

public Date getGeburtsdatum() {
	return geburtsdatum;
}

public void setGeburtsdatum(Date geburtsdatum) {
	if (geburtsdatum!= (Date) geburtsdatum) throw new IllegalArgumentException("Das Geburtsdatum muss Date sein");
	else
	this.geburtsdatum = geburtsdatum;
}

public String getPassnummer() {
	return passnummer;
}

public void setPassnummer(String passnummer) {
	if (passnummer.isEmpty() ) throw new IllegalArgumentException("Passnummer is empty!");
	else
	this.passnummer = passnummer;
}

public Date getPassAblaufsdatum() {
	return passAblaufsdatum;
}

public void setPassAblaufsdatum(Date passAblaufsdatum) {
	if (passAblaufsdatum!= (Date) passAblaufsdatum) throw new IllegalArgumentException("Das Ablaufsdatum muss Date sein");
	else
	this.passAblaufsdatum = passAblaufsdatum;
}

public String getNationalitaet() {
	return nationalitaet;
}

public void setNationalitaet(String nationalitaet) {
	if (nationalitaet.isEmpty() ) throw new IllegalArgumentException("Nationalitaet is empty!");
	else
	this.nationalitaet = nationalitaet;
}
 
 
}
