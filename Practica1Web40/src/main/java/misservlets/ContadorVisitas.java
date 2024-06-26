package misservlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContadorVisitas
 */
@WebServlet("/ContadorVisitas")
public class ContadorVisitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int cant=0;

    /**
     * Default constructor. 
     */
    public ContadorVisitas() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		ServletContext context = getServletContext();
//		Integer visitas = (Integer) context.getAttribute("contador");
//        		
//		if (visitas == null) {
//            visitas = 1; // Si es la primera visita, inicializa el contador en 1
//        } else {
//            visitas++; // Incrementa el contador
//        }        
//		
//		context.setAttribute("contador", visitas);
		
		cant++;
		
		PrintWriter out = response.getWriter();
        out.println("<html><head><title>Contador Visitas</title></head><body>");
        out.println("<h1>Bienvenido " + cant + "</h1>");
        out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
