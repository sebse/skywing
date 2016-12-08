package model;

import java.util.ArrayList;


public interface BuchungDAO {
	 public ArrayList<Buchung> getBuchungList();
	 public Buchung getBuchungbyId(int buchingid);
	 public boolean speichereBuchung(Buchung buchung);
	 public boolean loescheBuchung(Buchung buchung);
}
