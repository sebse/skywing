package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import model.flug.Flug;
import model.flug.dao.FlugDAO;
import model.flug.dao.SerializedFlugDAO;
import model.*;
import model.buchung.Buchung;
import model.buchung.dao.BuchungDAO;
import model.buchung.dao.SerializedBuchungDAO;

public class FlugManagementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String dataName = "C:\\Users\\Paul\\Documents\\Vorlesungen\\3. Semester\\SWE\\tomcat\\webapps\\skywings\\WEB-INF\\save\\saveflug.ser";
		String flugDataName = "../webapps/skywings/WEB-INF/save/saveflug";
		String buchungDataName = "../webapps/skywings/WEB-INF/save/savebuchung";
		FlugDAO flugDAO = new SerializedFlugDAO(flugDataName);
		BuchungDAO buchungDAO = new SerializedBuchungDAO(buchungDataName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd@HH:mm");
		SimpleDateFormat bdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat decf = new DecimalFormat("0.00");


		// Workflow to show all fluege
		ArrayList<String> flugnrList = new ArrayList<>();
		ArrayList<String> preisList = new ArrayList<>();
		ArrayList<Integer> freiplatzList = new ArrayList<>();
		ArrayList<String> abDatList = new ArrayList<>();
		ArrayList<String> anDatList = new ArrayList<>();
		ArrayList<String> abOrtList = new ArrayList<>();
		ArrayList<String> anOrtList = new ArrayList<>();
		ArrayList<Flug> flugList = flugDAO.getFlugList();

		for(Flug iter:flugList) {
			flugnrList.add(iter.getFlugnr());
			preisList.add(decf.format(iter.getSitzplatz().get(0).getPreis()));
			freiplatzList.add(iter.anzahlFreiplatz());
			abDatList.add(df.format(iter.getAbflugsdatum()));
			anDatList.add(df.format(iter.getAnkunftsdatum()));
			abOrtList.add(iter.getAbflugsort().getCode());
			anOrtList.add(iter.getAnkunftsort().getCode());
		}

		request.setAttribute("flugnrList", flugnrList);
		request.setAttribute("preisList", preisList);
		request.setAttribute("freiplatzList", freiplatzList);
		request.setAttribute("abDatList", abDatList);
		request.setAttribute("anDatList", anDatList);
		request.setAttribute("abOrtList", abOrtList);
		request.setAttribute("anOrtList", anOrtList);

		// Workflow to show all Buchungen
		ArrayList<String> b_datList = new ArrayList<>();
		ArrayList<String> b_emailList = new ArrayList<>();
		ArrayList<String> b_telList = new ArrayList<>();
		ArrayList<String> b_flugcodeList = new ArrayList<>();
		ArrayList<Integer> b_anzPassagierList = new ArrayList<>();
		ArrayList<Buchung> buchungList = buchungDAO.getBuchungList();

		for(Buchung iter:buchungList) {
			b_datList.add(bdf.format(iter.getBuchungsdatum()));
			b_emailList.add(iter.getEmail());
			b_telList.add(iter.getTelefonnummer());
			b_flugcodeList.add(iter.getFlugcode());
			b_anzPassagierList.add(iter.getPassagier().size());
		}

		request.setAttribute("datList", b_datList);
		request.setAttribute("emailList", b_emailList);
		request.setAttribute("teList", b_telList);
		request.setAttribute("flugcodeList", b_flugcodeList);
		request.setAttribute("anzPassagierList", b_anzPassagierList);


		request.getRequestDispatcher("/WEB-INF/classes/view/flugmanagement.jsp").include(request, response);
		response.setContentType("text/html");
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/skywings/management");
		response.setContentType("text/html");
    }
}