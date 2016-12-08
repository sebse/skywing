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
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList <Flug> leerlist = new ArrayList <Flug>();
	
	private String dataName; 

    public SerializedFlugDAO(String _dataName) {
        dataName = _dataName;


    }

    @SuppressWarnings("unchecked")
	public ArrayList<Flug> getFlugList() {
    	File file=new File(dataName);
		if (file.exists() && file.canRead()) {
		try{
			FileInputStream fileis = new FileInputStream(dataName);
			ObjectInputStream obis= new ObjectInputStream(fileis);
			if (obis!=null) {
				leerlist = (ArrayList<Flug>) obis.readObject();			
				obis.close();
				fileis.close();
			}
		}
			catch(IOException e){
				//System.out.println("Datei kann nicht erstellt werden! Problem mit Input!");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				//System.out.println("Erforderliche Klasse ist nicht in der Lage, wo classloader sucht!");
				e.printStackTrace();
			}
			
		}
		
		return leerlist;
	}



    public ArrayList<Flug> getFlugbyDatum (Date abflugsdatum) {
    	
    	ArrayList <Flug> suchlist= this.getFlugList();
    	ArrayList <Flug> newlist= new ArrayList<Flug>();
		for(int i=0; i<suchlist.size(); i++) {
			if (suchlist.get(i).getAbflugsdatum()==abflugsdatum) {
				newlist.add(suchlist.get(i)); 
			}

		}
		//System.out.println("Flug mit diesem Id konnte nicht gefunden werden!");
	
		return newlist;
    };
    
    public ArrayList<Flug> getFlugbyNummer(String nummer) {

    	ArrayList <Flug> suchlist= this.getFlugList();
    	ArrayList <Flug> newlist= new ArrayList<Flug>();
		for(int i=0; i<suchlist.size(); i++) {
			if (suchlist.get(i).getFlugnr()==nummer) {
				newlist.add(suchlist.get(i)); 
			}

		}
		//System.out.println("Flug mit diesem Id konnte nicht gefunden werden!");
	
		return newlist;
 
    };
    
    public Flug getFlugbyNrandDatum(String nr, Date abflugsdatum){
		ArrayList <Flug> suchlist= this.getFlugList();
		for(int i=0; i<suchlist.size(); i++) {
			if (suchlist.get(i).getFlugnr()==nr && suchlist.get(i).getAbflugsdatum()==abflugsdatum ) {
				return suchlist.get(i); 
			}

		}
	//System.out.println("Flug mit diesem Nummer oder Aflugsdatum konnte nicht gefunden werden!");
		return null;
    };

    public boolean speichereFlug(Flug _flug) {
    	if (this.getFlugbyId(_flug.getFlugid())!=null) 
			throw new IllegalArgumentException("Tut mir leid! Es gibt schon ein FLug mit diesem Id!");
		ArrayList <Flug> newlist= this.getFlugList();
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
        }
        else throw new IllegalArgumentException("Tut mir leid! Es gibt kein Flug mit dieser Id!");
       return true;
    	    
    	
    	
    }
   

	@Override
	public Flug getFlugbyId(int fld) {

		ArrayList <Flug> suchlist= this.getFlugList();
		for(int i=0; i<suchlist.size(); i++) {
			if (suchlist.get(i).getFlugid()==fld) {
				return suchlist.get(i); 
			}

		}
	//System.out.println("Flug mit dieser Id konnte nicht gefunden werden!");
		return null;
	}
}
