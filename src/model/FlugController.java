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
		if(flugDAO.speichereFlug(neuflug))
			return true;
		else
			return false;		
	}
	
	public boolean flugAendern(int flugid, Date neuabDatum, Date neuanDatum) throws IOException{
		flugDAO.getFlugbyId(flugid).setAbflugsdatum(neuabDatum);

		flugDAO.getFlugbyId(flugid).setAnkunftsdatum(neuanDatum);
		return true;
					
	}
	
	public boolean flugLoeschen(int flugid){
		if(flugDAO.loescheFlug(flugDAO.getFlugbyId(flugid)))
			return true;
		else
			return false;
	}
	
	//klassendiagramm ändern
	public Flug flugSuche(int flugid){
		return flugDAO.getFlugbyId(flugid);
	}

	// flugSuche overloading: return ArrayList<flug> for searching via time and place
	public ArrayList<flug> flugSuche(Flughafen abflugsort, Flughafen ankunftsort, Date abDatum) {
		ArrayList<flug> suchlist = new ArrayList<>();
		ArrayList<flug> flugList = flugDAO.getFlugbyDatum(abDatum);

		for(Flug iter:flugList)
			if(iter.getAbflugsort == abflugsort && iter.getAnkunftsort == ankunftsort)
				suchlist.add(iter);

		return suchlist;
	}
}


