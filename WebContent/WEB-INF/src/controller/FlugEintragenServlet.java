package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.flug.Flug;
import model.flug.dao.FlugDAO;
import model.flug.dao.SerializedFlugDAO;
import model.*;

public class FlugEintragenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/skywings/management");
		response.setContentType("text/html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);

        //String dataName = "C:\\Users\\Paul\\Documents\\Vorlesungen\\3. Semester\\SWE\\tomcat\\webapps\\skywings\\WEB-INF\\save\\saveflug.ser";
        //String baseFlughafenPath = "C:\\Users\\Paul\\Documents\\Vorlesungen\\3. Semester\\SWE\\tomcat\\webapps\\skywings\\WEB-INF\\save\\flughafen\\";
		String dataName = "../webapps/skywings/WEB-INF/save/saveflug";
		String baseFlughafenPath = "../webapps/skywings/WEB-INF/save/flughafen/";
        FlugDAO flugDAO = new SerializedFlugDAO(dataName);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd@HH:mm");

        String flugnr = request.getParameter("flugnr");
        
        String abflugsort = request.getParameter("ab_ort");
        String ankunftsort = request.getParameter("an_ort");
        Flughafen ab_flughafen = flughafenFromFile(baseFlughafenPath, abflugsort);
		Flughafen an_flughafen = flughafenFromFile(baseFlughafenPath, ankunftsort);

		Double preis = null;
        Date abflugsdatum = new Date();
        Date ankunftsdatum = new Date();
        
        try {
            preis = Double.parseDouble(request.getParameter("preis"));
            abflugsdatum = df.parse(request.getParameter("ab_datum"));
            ankunftsdatum = df.parse(request.getParameter("an_datum"));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        flugDAO.speichereFlug(new Flug(flugnr, preis, ab_flughafen, an_flughafen, abflugsdatum, ankunftsdatum));

		response.sendRedirect("/skywings/management");
		response.setContentType("text/html");
    }

    private Flughafen flughafenFromFile(String basepath, String fcode) {
		try {
			FileReader fr = new FileReader(basepath + fcode + ".txt");
			BufferedReader br = new BufferedReader(fr);
		
			Flughafen neuFlughafen = new Flughafen();

			String fname = br.readLine();
			String fland = br.readLine();
			String fstadt = br.readLine();

			/*if(fname.startsWith("Name: "))
				neuFlughafen.setName(fname.substring("Name: ".length()));
			else
				return null;

			neuFlughafen.setCode(fcode);

			if(fland.startsWith("Land: "))
				neuFlughafen.setLand(fland.substring("Land: ".length()));
			else
				return null;

			if(fstadt.startsWith("Stadt: "))
				neuFlughafen.setStadt(fstadt.substring("Stadt: ".length()));
			else
				return null;*/

			neuFlughafen.setName(fname);
			neuFlughafen.setCode(fcode);
			neuFlughafen.setLand(fland);
			neuFlughafen.setStadt(fstadt);

			return neuFlughafen;
		}
		catch(IOException e) {
			e.printStackTrace();
			return new Flughafen("Fuck", "you", "SWE", "Project");
		}
    }
}