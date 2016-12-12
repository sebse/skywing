package model;

import java.io.Serializable;
/**
 * This class is created to preserve some space for future improvement,
 * such as when the seats have different pricing (seats with leg room might be
 * more expensive, seats in different class may have different prices).
 * In the same time, the facilities provided with the specific seat could be shown
 * here as well (e.g. the dishes could be labeled as meat, fish, vegan etc.).
 * But those mentioned features are not implemented in this version.
 *
 * With this in mind, it makes sense to have this class here and now, rather than
 * including the seats as a normal string array in the Flug-class, which might result
 * in more complicated readjustment later on.
 */
public class Sitzplatz implements Serializable {
    private String flugnr;
    private int spalte;
    private int zeile;
    private double preis;
    private boolean isReserviert;

    public Sitzplatz(String _flugnr, int _spalte, int _zeile, double _preis, boolean _isReserviert) {
        flugnr = _flugnr;
        spalte = _spalte;
        zeile = _zeile;
        preis = _preis;
        isReserviert = _isReserviert;
    }

    public int getSpalte() { return spalte; }
    public void setSpalte(int spalte) { this.spalte = spalte; }

    public int getZeile() { return zeile; }
    public void setZeile(int zeile) { this.zeile = zeile; }

    public double getPreis() { return preis; }
    public void setPreis(double _preis) { this.preis = _preis; }

    public boolean isReserviert() { return isReserviert; }
    public void setReserviert(boolean _reserviert) { isReserviert = _reserviert; }

    public String getName() {
        String _name = new String();
        char A = 'A';
        A += spalte-1;

        _name += zeile;
        _name += A;

        return _name;
    }
}
