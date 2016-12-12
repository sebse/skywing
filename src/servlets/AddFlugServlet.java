package servlets;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Flug;
import model.FlugDAO;
import model.Flughafen;
import model.SerializedFlugDAO;

/**
 * This is a servlet to test adding Flug into lists.
 */
public class AddFlugServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        FlugDAO flugDAO = new SerializedFlugDAO("C:\\Users\\Axinya\\Desktop\\tomcat\\webapps\\addflug\\WEB-INF\\save\\saveflug");
        /*ArrayList<Flug> flugList = flugDAO.getFlugList();
        ArrayList<String> flugStrList = new ArrayList<>();

        for(Flug iter:flugList)
            flugStrList.add(iter.toString());

        request.setAttribute("flugStrList", flugStrList);*/

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        ArrayList<String> flugnrList = new ArrayList<>();
        ArrayList<Integer> freiplatzList = new ArrayList<>();
        ArrayList<String> abDatList = new ArrayList<>();
        ArrayList<String> anDatList = new ArrayList<>();
        ArrayList<Flug> flugList = flugDAO.getFlugList();

        //if(flugList == null)
        //    flugStrList.add("cant open file");

        for(Flug iter:flugList) {
            flugnrList.add(iter.getFlugnr());
            freiplatzList.add(iter.anzahlFreiplatz());
            abDatList.add(df.format(iter.getAbflugsdatum()));
            anDatList.add(df.format(iter.getAnkunftsdatum()));
        }

        //request.setAttribute("flugStrList", flugStrList);
        request.setAttribute("flugnrList", flugnrList);
        request.setAttribute("freiplatzList", freiplatzList);
        request.setAttribute("abDatList", abDatList);
        request.setAttribute("anDatList", anDatList);
        
        request.getRequestDispatcher("addflug.jsp").include(request, response);
        response.setContentType("text/html");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataName = "saveflug";
        FlugDAO flugDAO = new SerializedFlugDAO(dataName);
        Flughafen A = new Flughafen("N/A", "N/A", "N/A", "N/A");
        Flughafen B = new Flughafen("N/A", "N/A", "N/A", "N/A");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String flugnr = request.getParameter("flugnr");
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

        flugDAO.speichereFlug(new Flug(flugnr, preis, A, B, abflugsdatum, ankunftsdatum));

        //ArrayList<String> flugStrList = new ArrayList<>();
        ArrayList<String> flugnrList = new ArrayList<>();
        ArrayList<Integer> freiplatzList = new ArrayList<>();
        ArrayList<String> abDatList = new ArrayList<>();
        ArrayList<String> anDatList = new ArrayList<>();
        ArrayList<Flug> flugList = flugDAO.getFlugList();

        //if(flugList == null)
        //    flugStrList.add("cant open file");

        for(Flug iter:flugList) {
            flugnrList.add(iter.getFlugnr());
            freiplatzList.add(iter.anzahlFreiplatz());
            abDatList.add(df.format(iter.getAbflugsdatum()));
            anDatList.add(df.format(iter.getAnkunftsdatum()));
        }

        //request.setAttribute("flugStrList", flugStrList);
        request.setAttribute("flugnrList", flugnrList);
        request.setAttribute("freiplatzList", freiplatzList);
        request.setAttribute("abDatList", abDatList);
        request.setAttribute("anDatList", anDatList);

        request.getRequestDispatcher("addflug.jsp").forward(request, response);
        response.setContentType("text/html");
    }
}