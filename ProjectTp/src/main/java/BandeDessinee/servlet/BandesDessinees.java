package BandeDessinee.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BandesDessinees")
public class BandesDessinees extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> listeBandesDessinees;

    @Override
    public void init() throws ServletException {
        listeBandesDessinees = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Liste de nos bandes dessinées préférées</title></head><body>");
        out.println("<h4>Liste de nos bandes dessinées préférées</h4>");
        
        if (!listeBandesDessinees.isEmpty()) {
            out.println("<ul>");
            for (String bd : listeBandesDessinees) {
                out.println("<li>" + bd + "</li>");
            }
            out.println("</ul>");
        }
        
        out.println("<form method='POST' action='BandesDessinees'>");
        out.println("Entrez un nom de bande dessinée : <input type='text' name='bd'><br/><br/>");
        out.println("<input type='submit' name='action' value='Ajouter'>");
        out.println("<input type='submit' name='action' value='Retirer'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String bandeDessinee = request.getParameter("bd");
        String action = request.getParameter("action");
        String message = "";

        if (bandeDessinee != null && !bandeDessinee.trim().isEmpty()) {
            if ("Ajouter".equals(action)) {
                if (!listeBandesDessinees.contains(bandeDessinee)) {
                    listeBandesDessinees.add(bandeDessinee);
                    message = "Ajout réussi : " + bandeDessinee;
                } else {
                    message = "Cette bande dessinée est déjà dans la liste.";
                }
            } else if ("Retirer".equals(action)) {
                if (listeBandesDessinees.remove(bandeDessinee)) {
                    message = "Suppression réussie : " + bandeDessinee;
                } else {
                    message = "Cette bande dessinée n'était pas dans la liste.";
                }
            }
        } else {
            message = "Veuillez entrer un nom de bande dessinée.";
        }
        
        out.println("<html><head><title>Résultat</title></head><body>");
        out.println("<h4>" + message + "</h4>");
        out.println("<a href='" + request.getRequestURI() + "'>Retour au formulaire</a>");
        out.println("</body></html>");
    }
}
