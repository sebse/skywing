package model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class BuchungController 
{
	private BuchungDAO buchungDAO;

	public BuchungController(String pfad)
	{
		buchungDAO = new SerializedBuchungDAO(pfad);
	}
	
	
	public BuchungDAO getBuchungDAO()
	{
		return buchungDAO;
		
	}
	
	public void setBuchungDAO(BuchungDAO buchungDAO)
	{
		this.buchungDAO = buchungDAO;
	}
	
	public void addBuchung(Sitzplatz _sitz,Date passablauf,String vorname,String nation, String nachname, Date gdatum,
			String passnmr, String strasse,String ort,String plz,Flug _flug, String email, String telnmr,int anzpassagier)
	{
		Passagier _passagier = new Passagier(vorname,nachname,strasse,ort,plz,gdatum,passnmr,passablauf,nation);
		
		Ticket _ticket = new Ticket(_passagier,_flug,_sitz);
		Buchung newbuchung = new Buchung(email,telnmr,_flug,_passagier,_ticket);
		
		buchungDAO.speichereBuchung(newbuchung);
		
		
		
	}
	
	public void buchungModify(UUID bId, String vor, String nach, Date gdatum,
			String passnmr, String strasse,String ort,String plz, String email, String tnmr)
	{
		Buchung modbuchung = buchungDAO.getBuchungbyId(bId);
		modbuchung.getPassagier().setVorname(vor);
		modbuchung.getPassagier().setNachname(nach);
		modbuchung.getPassagier().setGeburtsdatum(gdatum);
		modbuchung.getPassagier().setPassnummer(passnmr);
		modbuchung.getPassagier().setStrasse(strasse);
		modbuchung.getPassagier().setOrt(ort);
		modbuchung.getPassagier().setPostleitzahl(plz);
		modbuchung.setTelefonnummer(tnmr);
		modbuchung.setEmail(email);
		
		buchungDAO.speichereBuchungModify(modbuchung);
	}
	
	public String buchungAnsehen(UUID buchungid)
	{
		return buchungDAO.getBuchungbyId(buchungid).toString();
		
	}
	
	public void delBuchung(UUID buchungid)
	{
		buchungDAO.getBuchungbyId(buchungid);
		
	}

}
