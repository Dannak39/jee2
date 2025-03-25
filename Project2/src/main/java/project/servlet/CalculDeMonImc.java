package project.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.model.Imc;

@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        double taille = Double.parseDouble(request.getParameter("taille"));
        double poids = Double.parseDouble(request.getParameter("poids"));

        Imc imc = new Imc(taille, poids);

        double resultatImc = imc.calcul();

        request.setAttribute("resultatImc", resultatImc);

        request.getRequestDispatcher("resultat.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response); 
    }
}
