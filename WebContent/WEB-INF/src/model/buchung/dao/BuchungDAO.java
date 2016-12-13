package model.buchung.dao;

import java.util.ArrayList;

import model.buchung.Buchung;

public interface BuchungDAO {
	 public ArrayList<Buchung> getBuchungList();
	 public Buchung getBuchungbyId(String buchingid);
	 public boolean speichereBuchung(Buchung buchung);
	 public boolean loescheBuchung(Buchung buchung);
}
