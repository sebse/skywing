package model;

import java.util.ArrayList;
import java.util.UUID;


public interface BuchungDAO {
	 public ArrayList<Buchung> getBuchungList();
	 public Buchung getBuchungbyId(UUID buchungid);
	 public boolean speichereBuchung(Buchung buchung);
	 public boolean loescheBuchung(Buchung buchung);
	 public boolean speichereBuchungModify(Buchung _buchung);
}
