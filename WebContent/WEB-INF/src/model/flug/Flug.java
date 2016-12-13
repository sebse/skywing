package model.flug;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;

/**
 * Class Flug represents the flight. It contains data associated with the airport(Flughafen)
 * as well as the departure date/time. Moreover, this class contains and controls the
 * corresponding seats it has inside.
 *
 * By this version, the number of seats is set to exact 200 seats for every flight.
 * In addtition, the plane always has 4 columns, 50 rows with no differentiated classes.
 * Seat number runs from 1A, 1B, 1C, ... , 50C, 50D.
 */
public class Flug implements Serializable {
    /*
     * For now, n_sitzplatz is static int, to serve the basic requirement that every of our flight(Flug)
     * provides the very same number of seats(Sitzplatz). If one day (which might not be in the near future)
     * the company wants to have flights with different seat number, it would not be difficult to readjust the class.
     */
    private static final int N_Sitzplatz = 200;
    private static final int N_Sitzspalte = 4;
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String flugnr;
    private ArrayList<Sitzplatz> sitzplatz;
    private Flughafen abflugsort;
    private Flughafen ankunftsort;
    private Date abflugsdatum;
    private Date ankunftsdatum;

    public Flug(String _flugnr, double std_sitzpreis, Flughafen _abflugsort, Flughafen _ankunftsort,
                Date _abflugsdatum, Date _ankunftsdatum) {
        flugnr = _flugnr;
        abflugsort = _abflugsort;
        ankunftsort = _ankunftsort;
        abflugsdatum = _abflugsdatum;
        ankunftsdatum = _ankunftsdatum;

        sitzplatz = new ArrayList<>();
        for(int i=0; i < N_Sitzplatz; i++)
            sitzplatz.add(new Sitzplatz(flugnr, (i%N_Sitzspalte)+1, (i/N_Sitzspalte)+1, std_sitzpreis, false));
    }

    public String getFlugnr() { return flugnr; }
    public void setFlugnr(String _flugnr) { this.flugnr = _flugnr; }

    public ArrayList<Sitzplatz> getSitzplatz() { return sitzplatz; }
    public void setSitzplatz(ArrayList<Sitzplatz> _sitzplatz) { this.sitzplatz = _sitzplatz; }

    public Flughafen getAbflugsort() { return abflugsort; }
    public void setAbflugsort(Flughafen _abflugsort) { this.abflugsort = _abflugsort; }

    public Flughafen getAnkunftsort() { return ankunftsort; }
    public void setAnkunftsort(Flughafen _ankunftsort) { this.ankunftsort = _ankunftsort; }

    public Date getAbflugsdatum() { return abflugsdatum; }
    public void setAbflugsdatum(Date _abflugsdatum) { this.abflugsdatum = _abflugsdatum; }

    public Date getAnkunftsdatum() { return ankunftsdatum; }
    public void setAnkunftsdatum(Date _ankunftsdatum) { this.ankunftsdatum = _ankunftsdatum; }

    public int getSitzplatzIndex(Sitzplatz _sitzplatz) { return (_sitzplatz.getSpalte()-1)*N_Sitzspalte + _sitzplatz.getZeile()-1; }

    public int anzahlFreiplatz() {
        int counter = 0;

        for(int i=0; i < N_Sitzplatz; i++)
            if(!sitzplatz.get(i).isReserviert())
                counter++;

        return counter;
    }

    public Sitzplatz nextFreiplatz() {
    	for(Sitzplatz iter:sitzplatz)
        	if(!iter.isReserviert())
        		return iter;

        return null;
    }

    public String toString() {
        String rts = new String();
        rts += flugnr + " " +
                df.format(abflugsdatum) + " " +
                df.format(ankunftsdatum) + " " +
                "Sitzplatz: " + anzahlFreiplatz();

        return rts;
    }
}
