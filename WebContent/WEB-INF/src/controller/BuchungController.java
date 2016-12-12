package model;
import java.util.ArrayList;
import java.util.Calendar;

public class BuchungController 
{
	private BuchungDAO buchungDAO;

	public BuchungController(String pfad)
	{
		//buchungDAO = new SerializedBuchungDAO(pfad);
	}
	
	
	public BuchungDAO getBuchungDAO()
	{
		return buchungDAO;
		
	}
	
	public void setBuchungDAO(BuchungDAO buchungDAO)
	{
		this.buchungDAO = buchungDAO;
	}
	
	public void addBuchung(int buchungsId, int flugId, String vorname, String nachname, Calendar gdatum,
			String passnmr, String adresse, String email, String telnmr,int anzpassagier)
	{
		Flug flug;
		Buchung buchung = buchungDAO.getBuchungbyId(buchungsId);
		
		
	}
	
	public void buchungModify(int bId, int fId, String vor, String nach, Calendar gdatum,
			String passnmr, String adresse, String email, String tnmr, double preis)
	{
		
	}
	
	public String buchungAnsehen(int buchungid, String name)
	{
		return null;
		
	}
	
	public void delBuchung(int buchungid)
	{
		
	}

}
