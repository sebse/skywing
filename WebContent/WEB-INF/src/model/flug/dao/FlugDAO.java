package model.flug.dao;

import java.util.ArrayList;
import java.util.Date;
import model.flug.Flug;

/**
 * FlugDAO is a data access interface to Flug-class. This provides accessibility
 * to the data store and would be implemented in more concrete form(s),
 * in this project, we might implement FlugDAO only for serializable object access.
 */
public interface FlugDAO {
    public ArrayList<Flug> getFlugList();
    //public ArrayList<Flug> getFlugbyNummer(String nummer);
    //public ArrayList<Flug> getFlugbyDatum(Date abflugsdatum);
    public Flug getFlugbyNrandDatum(String nr, Date abflugsdatum);
    public boolean speichereFlug(Flug _flug);
    public boolean loescheFlug(String flugnr, Date abflugsdatum);
    public boolean reserveFlug(String flugnr, Date abflugsdatum, int sitzplatzIdx, boolean val);
    //public void modFlug(Flug _flug);
}
