package model;

import java.io.IOException;
import java.util.Date;

public class FlugController  {
 
	FlugDAO flugDAO;
	
	
	public FlugController(String pfad){
		
		
		flugDAO = new SerializedFlugDAO(pfad);
		
	}
	
	public FlugDAO getFlugDAO(){
		
		return flugDAO;
		
	}
	
	public void setFlugDAO(FlugDAO flugDAO){
		this.flugDAO=flugDAO;
		
	}
	
		
	public boolean flugEintragen(int flugid, String flugnummer, double preis, Date abDatum, Date anDatum, Flughafen abflugsort, Flughafen ankunftsort) throws IOException{
		Flug neuflug= new Flug (flugid, flugnummer, preis, abflugsort, ankunftsort, abDatum, anDatum );
		flugDAO.speichereFlug(neuflug);
		return true;
		
	}
	
	public boolean flugAendern(int flugid, Date neuabDatum, Date neuanDatum) throws IOException{
		flugDAO.getFlugbyId(flugid).setAbflugsdatum(neuabDatum);

		flugDAO.getFlugbyId(flugid).setAnkunftsdatum(neuanDatum);
		return true;
					
	}
	
	public void flugLoeschen(int flugid){
		flugDAO.loescheFlug(flugDAO.getFlugbyId(flugid));
		
	}
	
	//klassendiagramm ändern
	public Flug flugSuche(int flugid){
		return flugDAO.getFlugbyId(flugid);
		
	}
//FLugliste	
	
}


