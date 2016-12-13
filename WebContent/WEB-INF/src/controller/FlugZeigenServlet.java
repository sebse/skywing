package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import model.flug.Flug;
import model.flug.dao.FlugDAO;
import model.flug.dao.SerializedFlugDAO;
import model.Flughafen;

public class FlugZeigenServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String dataName = "C:\\Users\\Paul\\Documents\\Vorlesungen\\3. Semester\\SWE\\tomcat\\webapps\\skywings\\WEB-INF\\save\\saveflug.ser";
		String dataName = "../webapps/skywings/WEB-INF/save/saveflug";
		FlugDAO flugDAO = new SerializedFlugDAO(dataName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd@HH:mm");
		DecimalFormat decf = new DecimalFormat("0.00");

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

		request.getRequestDispatcher("/WEB-INF/classes/view/fluglist.jsp").include(request, response);
		response.setContentType("text/html");
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/skywings/flugliste");
		response.setContentType("text/html");
    }
}