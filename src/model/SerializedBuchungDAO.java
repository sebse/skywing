package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class SerializedBuchungDAO implements BuchungDAO, Serializable{

	
	private static final long serialVersionUID = 1L;
    
	private ArrayList <Buchung> leerlist = new ArrayList <Buchung>();
	
	private String dataName; 

    public SerializedBuchungDAO(String _dataName) {
        dataName = _dataName;


    }
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Buchung> getBuchungList() {
		File file=new File(dataName);
		if (file.exists() && file.canRead()) {
		try{
			FileInputStream fileis = new FileInputStream(dataName);
			ObjectInputStream obis= new ObjectInputStream(fileis);
			if (obis!=null) {
				leerlist = (ArrayList<Buchung>) obis.readObject();			
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

	@Override
	public Buchung getBuchungbyId(UUID buchingid) {
		ArrayList <Buchung> suchlist= this.getBuchungList();
		for(int i=0; i<suchlist.size(); i++) {
			if (suchlist.get(i).getBuchungid()==buchingid) {
				return suchlist.get(i); 
			}

		}
	//System.out.println("Buchung mit dieser Id konnte nicht gefunden werden!");
		return null;
	}

	@Override
	public boolean speichereBuchung(Buchung buchung) {
		if (this.getBuchungbyId(buchung.getBuchungid())!=null) 
			throw new IllegalArgumentException("Tut mir leid! Es gibt schon eine Buchung mit dieser Id!");
		ArrayList <Buchung> newlist= this.getBuchungList();
		newlist.add(buchung);
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
	public boolean speichereBuchungModify(Buchung _buchung){
    	ArrayList<Buchung> modifylist = this.getBuchungList();
    	
        for(int i = 0; i < modifylist.size(); ++i) {
            if(modifylist.get(i)==_buchung)
            {
            	modifylist.get(i).setEmail(_buchung.getEmail());
            	modifylist.get(i).setTelefonnummer(_buchung.getTelefonnummer());
            	modifylist.get(i).getPassagier().setNachname(_buchung.getPassagier().getNachname());
            	modifylist.get(i).getPassagier().setVorname(_buchung.getPassagier().getVorname());
            	modifylist.get(i).getPassagier().setOrt(_buchung.getPassagier().getOrt());
            	modifylist.get(i).getPassagier().setStrasse(_buchung.getPassagier().getStrasse());
            	modifylist.get(i).getPassagier().setPostleitzahl(_buchung.getPassagier().getPostleitzahl());
            	modifylist.get(i).getPassagier().setGeburtsdatum(_buchung.getPassagier().getGeburtsdatum());
            	modifylist.get(i).getPassagier().setPassnummer(_buchung.getPassagier().getPassnummer());
            	
            }
        }
        
    	
    	try {
			FileOutputStream fileos = new FileOutputStream(dataName);
			ObjectOutputStream obos= new ObjectOutputStream(fileos);
			if (obos!=null) {
				obos.writeObject(modifylist);			
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

	@Override
	public boolean loescheBuchung(Buchung buchung) {
		if (buchung != null) {
            ArrayList<Buchung> speicherliste = this.getBuchungList();
            for (Buchung buchung2 : speicherliste) {
                if (buchung.getBuchungid() == buchung2.getBuchungid()) {
                    buchung = buchung2;
                    break;
                }
            }

            while (speicherliste.contains(buchung)) {
                speicherliste.remove(buchung);
            }

            try {
     
                FileOutputStream fileos = new FileOutputStream(dataName);
            
                ObjectOutputStream obos = new ObjectOutputStream(fileos);
                obos.writeObject(speicherliste);
                fileos.close();
                obos.close();
                //System.out.println("Geloescht");
    
            } catch (IOException e) {
            	//System.out.println("Problem mit Output!");
                e.printStackTrace();
                return false;
            }
        }
        else throw new IllegalArgumentException("Tut mir leid! Es gibt keine Buchung mit dieser Id!");
       return true;
    	    
		
	}
	

}
