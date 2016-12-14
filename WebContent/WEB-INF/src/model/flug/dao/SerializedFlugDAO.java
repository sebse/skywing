package model.flug.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatException;
import java.text.SimpleDateFormat;

import model.flug.Flug;
import model.*;

/**
 * SerializedFlugDAO is the data access class of Flug serialized objects.
 * This implements FlugDAO interface.
 */
public class SerializedFlugDAO implements FlugDAO {
    String dataName;

    public SerializedFlugDAO(String _dataName) {
        dataName = _dataName;

        if(!(new File(dataName).exists())) {
            ArrayList<Flug> flugList;

            try {                
                FileOutputStream fos = new FileOutputStream(dataName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                flugList = new ArrayList<>();
                oos.writeObject(flugList);

                oos.close();
                fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Flug> getFlugList() {
        ArrayList<Flug> flugList = new ArrayList<>();
        //ArrayList<Flug> flugList = null;

        try {
            FileInputStream fis = new FileInputStream(dataName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            flugList = (ArrayList<Flug>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch(IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flugList;
    }

    //public List<Flug> getFlugbyNummer(String nummer);
    //public List<Flug> getFlugbyDatum(Calendar abflugsdatum);
    public Flug getFlugbyNrandDatum(String nr, Date abflugsdatum){
		ArrayList<Flug> flugList = this.getFlugList();
        int pos = -1;

        for(int i=0; i < flugList.size(); i++)
            if(flugList.get(i).getFlugnr().equals(nr) && flugList.get(i).getAbflugsdatum().equals(abflugsdatum))
                pos = i;

        if(pos >= 0)
            return flugList.get(pos);
        else
		  return null;
    }

    public boolean speichereFlug(Flug _flug) {
        ArrayList<Flug> flugList = new ArrayList<>();
        SimpleDateFormat df_notime = new SimpleDateFormat("yyyy-MM-dd");

        try {
            FileInputStream fis = new FileInputStream(dataName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            flugList = (ArrayList<Flug>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch(IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        // flight with the same flight number cannot be available in the same day
        for(Flug iter: flugList)
            if(iter.getFlugnr().equals(_flug.getFlugnr()) && df_notime.format(iter.getAbflugsdatum()).equals(df_notime.format(_flug.getAbflugsdatum())))
                return false;
                //throw new IllegalFormatException("Die Fluege mit derselben Nummerm koennen nicht im selben Tag fliegen.");

        flugList.add(_flug);

        try {
            FileOutputStream fos = new FileOutputStream(dataName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(flugList);

            oos.close();
            fos.close();

            return true;
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loescheFlug(String flugnr, Date abflugsdatum) {
        ArrayList<Flug> flugList = this.getFlugList();
        int delPos = -1;

        for(int i=0; i < flugList.size(); i++)
            if(flugList.get(i).getFlugnr().equals(flugnr) && flugList.get(i).getAbflugsdatum().equals(abflugsdatum))
                delPos = i;

        if(delPos >= 0)
            flugList.remove(delPos);

        try {
            FileOutputStream fos = new FileOutputStream(dataName, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(flugList);

            oos.close();
            fos.close();

            return true;
        }
        catch(IOException|IllegalFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reserveFlug(String flugnr, Date abflugsdatum, int sitzplatzIdx, boolean val) {
        ArrayList<Flug> flugList = this.getFlugList();
        int pos = -1;

        for(int i=0; i < flugList.size(); i++)
            if(flugList.get(i).getFlugnr().equals(flugnr) && flugList.get(i).getAbflugsdatum().equals(abflugsdatum))
                pos = i;

        if(pos < 0)
            return false;

        flugList.get(pos).getSitzplatz().get(sitzplatzIdx).setReserviert(val);

        try {
            FileOutputStream fos = new FileOutputStream(dataName, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(flugList);

            oos.close();
            fos.close();

            return true;
        }
        catch(IOException|IllegalFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
    //public void modFlug(Flug _flug);
}
