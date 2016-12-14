package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;
import model.flug.Flug;
import model.flug.dao.*;
import model.buchung.Buchung;
import model.buchung.dao.*;

public class FlugBuchungServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selFlug = request.getParameter("sel-flug");
		Integer n_passagier = Integer.parseInt(request.getParameter("anz_pass"));

		request.setAttribute("selFlug",selFlug);
		request.setAttribute("n_passagier", n_passagier);

		if(selFlug == null)
			throw new IllegalArgumentException("Sie haben das Flug noch nicht gewaelht.");

		request.getRequestDispatcher("/WEB-INF/classes/view/buchung-add-passagier.jsp").include(request, response);
		response.setContentType("text/html");
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String buchungDataName = "../webapps/skywings/WEB-INF/save/savebuchung";
    	String flugDataName = "../webapps/skywings/WEB-INF/save/saveflug";
    	BuchungDAO buchungDAO = new SerializedBuchungDAO(buchungDataName);
    	FlugDAO flugDAO = new SerializedFlugDAO(flugDataName);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat abflug_sdf = new SimpleDateFormat("yyyy-MM-dd@HH:mm");
    	DecimalFormat df = new DecimalFormat("000");

    	String selFlug = request.getParameter("selectFlug");
    	Integer n_passagier = Integer.parseInt(request.getParameter("numPassagier"));

    	String tel = request.getParameter("tel");
    	String email = request.getParameter("email");
    	String[] selectedFlug = request.getParameter("sel-flug").split("#");
    	String flugnr = selectedFlug[0];
		Date abflugsdatum = new Date();
		try {
			abflugsdatum = abflug_sdf.parse(selectedFlug[1]);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

    	ArrayList<Buchung> buchungList = buchungDAO.getBuchungList();
    	Flug flug = flugDAO.getFlugbyNrandDatum(flugnr, abflugsdatum);
    	//ArrayList<Flug> flugList = flugDAO.getFlugList();
    	//Flug flug = flugList.get(0);
    	ArrayList<Passagier> passagier = new ArrayList<>();
    	ArrayList<Ticket> tickets = new ArrayList<>();
    	Date buchungsdatum = new Date();

    	String buchungid = selFlug + "_" + df.format(buchungList.size()+1);

    	if (flug.anzahlFreiplatz() < n_passagier)
    		throw new IllegalArgumentException("Zu viele Passagieren fuer dieses Flug");

		for(int i=0; i < n_passagier; i++) {
			String req_vorname = "vorname" + i;
			String req_nachname = "nachname" + i;
			String req_strasse = "strasse" + i;
			String req_ort = "ort" + i;
			String req_plz = "plz" + i;
			String req_g_datum = "g_datum" + i;
			String req_passnr = "passnr" + i;
			String req_passAbDat = "passAbDat" + i;
			String req_nation = "nation" + i;

			String vorname = request.getParameter(req_vorname);
			String nachname = request.getParameter(req_nachname);
			String strasse = request.getParameter(req_strasse);
			String ort = request.getParameter(req_ort);
			String plz = request.getParameter(req_plz);
			String passnr = request.getParameter(req_passnr);
			String nation = request.getParameter(req_nation);
			Date g_datum = new Date();
			Date passAbDat = new Date();

			String ticketnr = buchungid + df.format(i);

			try {
				g_datum = sdf.parse(request.getParameter(req_g_datum));
				passAbDat = sdf.parse(request.getParameter(req_passAbDat));
			}
			catch(ParseException e) {
				e.printStackTrace();
			}

			passagier.add(new Passagier(vorname, nachname, strasse, ort, plz, g_datum, passnr, passAbDat, nation));
			tickets.add(new Ticket(ticketnr, passnr, selFlug, flug.nextFreiplatz().getSpalte(), flug.nextFreiplatz().getZeile()));
			flugDAO.reserveFlug(flugnr, abflugsdatum, flug.getSitzplatzIndex(flug.nextFreiplatz()), true);
		}

		request.setAttribute("buchungid", buchungid);

		if(buchungDAO.speichereBuchung(new Buchung(buchungid, buchungsdatum, email, tel, selFlug, passagier, tickets))) {
			request.getRequestDispatcher("/WEB-INF/classes/view/buchung-erfolg.jsp").include(request, response);
			response.setContentType("text/html");
		} else {
			request.getRequestDispatcher("login-failed.html").include(request, response);
			response.setContentType("text/html");
		}
    }
}