package model;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SerializedFlugDAO is the data access class of Flug serialized objects.
 * This implements FlugDAO interface.
 */
public class SerializedFlugDAO implements FlugDAO, Serializable{
	private static final long serialVersionUID = 1L;
	private String dataName; 

	
	
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

    @SuppressWarnings("unchecked")
	public ArrayList<Flug> getFlugList() {
		ArrayList<Flug> flugList = new ArrayList<>();

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



    public ArrayList<Flug> getFlugbyDatum (Date abflugsdatum) {
    	ArrayList<Flug> suchlist = this.getFlugList();
    	ArrayList<Flug> newlist = new ArrayList<Flug>();
		
		for(Flug iter:suchlist)
			if(iter.getAbflugsdatum() == abflugsdatum)
				newlist.add(iter);
		//System.out.println("Flug mit diesem Id konnte nicht gefunden werden!");
	
		return newlist;
    };
    
    public ArrayList<Flug> getFlugbyNummer(String nummer) {
    	ArrayList <Flug> suchlist= this.getFlugList();
    	ArrayList <Flug> newlist= new ArrayList<Flug>();
		
		for(Flug iter:suchlist)
			if(iter.getFlugnr() == nummer)
				newlist.add(iter);
		//System.out.println("Flug mit diesem Id konnte nicht gefunden werden!");
	
		return newlist;
 
    };
    
    public Flug getFlugbyNrandDatum(String nr, Date abflugsdatum){
		ArrayList<Flug> suchlist = this.getFlugList();

		for(Flug iter:suchlist)
			if(iter.getFlugnr() == nr && iter.getAbflugsdatum() == abflugsdatum)
				return iter;
		//System.out.println("Flug mit diesem Nummer oder Aflugsdatum konnte nicht gefunden werden!");

		return null;
    };

    public boolean speichereFlug(Flug _flug) {
    	if (this.getFlugbyId(_flug.getFlugid())!=null) 
			throw new IllegalArgumentException("Tut mir leid! Es gibt schon ein FLug mit diesem Id!");
		ArrayList<Flug> newlist= this.getFlugList();
		newlist.add(_flug);
		try {
			FileOutputStream fileos = new FileOutputStream(dataName);
			ObjectOutputStream obos= new ObjectOutputStream(fileos);
			if (obos!=null) {
				obos.writeObject(newlist);			
				obos.close();
				fileos.close();
			}
		}
		catch(IOException e){
			//System.out.println("Datei kann nicht erstellt werden! Problem mit Output!");
			e.printStackTrace();
			return false;
		}
		return true;
    }
    
    

    
    
    public boolean loescheFlug(Flug flug) {
    	if (flug != null) {
            ArrayList<Flug> speicherliste = this.getFlugList();
            for (Flug flug2 : speicherliste) {
                if (flug.getFlugid() == flug2.getFlugid()) {
                    flug = flug2;
                    break;
                }
            }

            while (speicherliste.contains(flug)) {
                speicherliste.remove(flug);
            }

            try {
                FileOutputStream fileos = new FileOutputStream(dataName);
            
                ObjectOutputStream obos = new ObjectOutputStream(fileos);
                obos.writeObject(speicherliste);
                fileos.close();
                obos.close();
                //System.out.println("Geloescht");
            } catch (IOException e) {
            	//System.out.println("Datei kann nicht erstellt werden! Problem mit Output!");
                e.printStackTrace();
                return false;
            }
        } else throw new IllegalArgumentException("Tut mir leid! Es gibt kein Flug mit dieser Id!");

		return true;
    }
   

	@Override
	public Flug getFlugbyId(int flugId) {
		ArrayList<Flug> suchlist = this.getFlugList();
		
		for(Flug iter:suchlist)
			if(iter.getFlugid() == flugId)
				return iter;
		//System.out.println("Flug mit dieser Id konnte nicht gefunden werden!");

		return null;
	}
}
